package com.example.moneymod.command;

import com.example.moneymod.money.MoneyProvider;
import com.example.moneymod.util.CoinUtils;
import com.example.moneymod.util.TransactionLogger;
import com.example.moneymod.item.ModItems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class BankCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        // 💰 DEPOSIT
        dispatcher.register(Commands.literal("deposit")
                .executes(ctx -> {

                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    int total = 0;

                    for (var stack : player.getInventory().items) {
                        int value = CoinUtils.getValue(stack.getItem());

                        if (value > 0) {
                            total += value * stack.getCount();
                            stack.setCount(0);
                        }
                    }

                    int finalTotal = total;

                    player.getCapability(MoneyProvider.MONEY).ifPresent(m -> {
                        m.addMoney(finalTotal);
                    });

                    TransactionLogger.log(player.getName().getString() + " deposited $" + finalTotal);

                    player.sendSystemMessage(Component.literal("Deposited $" + finalTotal));
                    return 1;
                }));

        // 🏧 WITHDRAW
        dispatcher.register(Commands.literal("withdraw")
                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                        .executes(ctx -> {

                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                            player.getCapability(MoneyProvider.MONEY).ifPresent(money -> {

                                if (money.getBalance() >= amount) {

                                    money.addMoney(-amount);

                                    int remaining = amount;

                                    int gold = remaining / 100;
                                    remaining %= 100;

                                    int silver = remaining / 10;
                                    remaining %= 10;

                                    int copper = remaining;

                                    if (gold > 0)
                                        player.addItem(new ItemStack(ModItems.GOLD_COIN.get(), gold));

                                    if (silver > 0)
                                        player.addItem(new ItemStack(ModItems.SILVER_COIN.get(), silver));

                                    if (copper > 0)
                                        player.addItem(new ItemStack(ModItems.COPPER_COIN.get(), copper));

                                    player.sendSystemMessage(Component.literal("Withdrew $" + amount));

                                } else {
                                    player.sendSystemMessage(Component.literal("Not enough money"));
                                }
                            });

                            return 1;
                        })));
    }
}