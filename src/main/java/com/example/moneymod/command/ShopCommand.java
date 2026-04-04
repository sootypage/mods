package com.example.moneymod.command;

import com.example.moneymod.shop.ShopMenu;
import com.example.moneymod.shop.SellPrices;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleMenuProvider;

import net.minecraftforge.network.NetworkHooks;

public class ShopCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        // 🛒 OPEN SHOP
        dispatcher.register(Commands.literal("shop")
                .executes(ctx -> {

                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    NetworkHooks.openScreen(
                            player,
                            new SimpleMenuProvider(
                                    (id, inv, p) -> new ShopMenu(id, inv, null),
                                    Component.literal("Shop")
                            )
                    );

                    return 1;
                }));

        // 💰 SELL ALL
        dispatcher.register(Commands.literal("sell")
                .executes(ctx -> {

                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    int total = 0;

                    for (int i = 0; i < player.getInventory().items.size(); i++) {

                        ItemStack stack = player.getInventory().items.get(i);

                        if (!stack.isEmpty()) {

                            int value = SellPrices.getPrice(stack);

                            if (value > 0) {
                                total += value * stack.getCount();
                                player.getInventory().items.set(i, ItemStack.EMPTY);
                            }
                        }
                    }

                    player.sendSystemMessage(
                            Component.literal("Sold everything for $" + total)
                    );

                    return 1;
                }));
    }
}