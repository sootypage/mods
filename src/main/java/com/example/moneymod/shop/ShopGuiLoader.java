package com.example.moneymod.shop;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.NetworkHooks;

public class ShopGuiLoader {

    /**
     * Opens the main shop GUI for a player
     */
    public static void open(ServerPlayer player) {

        NetworkHooks.openScreen(
                player,
                new SimpleMenuProvider(
                        (id, inv, p) -> new ShopMenu(id, inv, null),
                        Component.literal("🛒 Marketplace")
                )
        );
    }
}