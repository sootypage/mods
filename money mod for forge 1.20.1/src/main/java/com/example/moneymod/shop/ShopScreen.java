package com.example.moneymod.shop;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ShopScreen extends AbstractContainerScreen<ShopMenu> {

    public ShopScreen(ShopMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();

        int startX = leftPos + 10;
        int startY = topPos + 10;

        int cols = 9;

        for (int i = 0; i < ShopManager.ITEMS.size(); i++) {

            int row = i / cols;
            int col = i % cols;

            int x = startX + (col * 18);
            int y = startY + (row * 20);

            int index = i;
            ShopItem item = ShopManager.ITEMS.get(i);

            addRenderableWidget(
                    Button.builder(
                                    Component.literal("$" + item.price),
                                    btn -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, index)
                            )
                            .pos(x, y)
                            .size(18, 18)
                            .build()
            );
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    }
}