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

        // 💰 DEPOSIT (UNCHANGED - already good)
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


        // 🏧 WITHDRAW (FULLY UPGRADED)
        dispatcher.register(Commands.literal("withdraw")
                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                        .executes(ctx -> {

                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                            player.getCapability(MoneyProvider.MONEY).ifPresent(money -> {

                                if (money.getBalance() >= amount) {

                                    money.addMoney(-amount);

                                    int remaining = amount;

                                    // 💎 HIGHEST → LOWEST
                                    remaining = give(player, remaining, 10000, ModItems.MYTHIC_COIN.get());
                                    remaining = give(player, remaining, 5000, ModItems.RUBY_COIN.get());
                                    remaining = give(player, remaining, 1000, ModItems.DIAMOND_COIN.get());
                                    remaining = give(player, remaining, 500, ModItems.PLATINUM_COIN.get());
                                    remaining = give(player, remaining, 100, ModItems.GOLD_COIN.get());
                                    remaining = give(player, remaining, 10, ModItems.SILVER_COIN.get());
                                    remaining = give(player, remaining, 1, ModItems.COPPER_COIN.get());

                                    player.sendSystemMessage(Component.literal("Withdrew $" + amount));

                                } else {
                                    player.sendSystemMessage(Component.literal("Not enough money"));
                                }
                            });

                            return 1;
                        })));
    }

    // 💡 HELPER METHOD (CLEAN AF)
    private static int give(ServerPlayer player, int remaining, int value, net.minecraft.world.item.Item coin) {

        int count = remaining / value;

        if (count > 0) {
            player.addItem(new ItemStack(coin, count));
            remaining %= value;
        }

        return remaining;
    }
}