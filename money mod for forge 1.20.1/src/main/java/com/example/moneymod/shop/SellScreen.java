package com.example.moneymod.shop;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SellScreen extends AbstractContainerScreen<SellMenu> {

    public SellScreen(SellMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();

        // 💰 increase price
        addRenderableWidget(
                Button.builder(Component.literal("+10"), b -> menu.price += 10)
                        .pos(leftPos + 10, topPos + 20)
                        .size(60, 20)
                        .build()
        );

        // 💰 decrease price
        addRenderableWidget(
                Button.builder(Component.literal("-10"), b -> menu.price = Math.max(0, menu.price - 10))
                        .pos(leftPos + 10, topPos + 45)
                        .size(60, 20)
                        .build()
        );

        // 🛒 LIST ITEM (REAL SELL LOGIC)
        addRenderableWidget(
                Button.builder(Component.literal("Sell Item"), b -> {

                            if (minecraft.player == null) return;

                            minecraft.player.connection.sendCommand(
                                    "sell " + menu.price
                            );
                        })
                        .pos(leftPos + 90, topPos + 90)
                        .size(80, 20)
                        .build()
        );
    }

    @Override
    protected void renderBg(GuiGraphics g, float pt, int mx, int my) {
    }
}