package com.example.moneymod.command;

import com.example.moneymod.shop.*;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.SimpleMenuProvider;

public class ShopCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("shop")
                .executes(ctx -> {

                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    NetworkHooks.openScreen(player,
                            new SimpleMenuProvider(
                                    (id, inv, p) -> new ShopMenu(id, inv),
                                    Component.literal("Shop")
                            )
                    );

                    return 1;
                }));

        dispatcher.register(Commands.literal("sell")
                .executes(ctx -> {

                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    NetworkHooks.openScreen(player,
                            new SimpleMenuProvider(
                                    (id, inv, p) -> new SellMenu(id, inv),
                                    Component.literal("Sell Item")
                            )
                    );

                    return 1;
                }));
    }
}