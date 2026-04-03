package com.example.moneymod.command;

import com.example.moneymod.money.MoneyProvider;
import com.example.moneymod.util.TransactionLogger;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public class MoneyCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        // 📊 BALANCE
        dispatcher.register(Commands.literal("bal")
                .executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    player.getCapability(MoneyProvider.MONEY).ifPresent(m -> {
                        player.sendSystemMessage(Component.literal("Balance: $" + m.getBalance()));
                    });

                    return 1;
                }));

        // 💸 PAY
        dispatcher.register(Commands.literal("pay")
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                .executes(ctx -> {

                                    ServerPlayer sender = ctx.getSource().getPlayerOrException();
                                    ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                    sender.getCapability(MoneyProvider.MONEY).ifPresent(sm -> {

                                        if (sm.getBalance() >= amount) {

                                            sm.addMoney(-amount);

                                            target.getCapability(MoneyProvider.MONEY).ifPresent(tm -> {
                                                tm.addMoney(amount);
                                            });

                                            TransactionLogger.log(
                                                    sender.getName().getString() +
                                                            " paid $" + amount +
                                                            " to " + target.getName().getString()
                                            );

                                        } else {
                                            sender.sendSystemMessage(Component.literal("Not enough money"));
                                        }
                                    });

                                    return 1;
                                }))));

        // 🛠️ SET MONEY (OP ONLY)
        dispatcher.register(Commands.literal("setmoney")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                .executes(ctx -> {

                                    ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                    target.getCapability(MoneyProvider.MONEY).ifPresent(money -> {
                                        money.setBalance(amount);
                                    });

                                    ctx.getSource().sendSystemMessage(
                                            Component.literal("Set " + target.getName().getString() + " balance to $" + amount)
                                    );

                                    target.sendSystemMessage(
                                            Component.literal("Your balance was set to $" + amount)
                                    );

                                    return 1;
                                }))));
    }
}