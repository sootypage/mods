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

        // 🔹 CATEGORY BUTTONS
        addRenderableWidget(Button.builder(Component.literal("All"), b -> switchCat(ShopCategory.ALL))
                .pos(leftPos + 10, topPos - 20).size(40, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Blocks"), b -> switchCat(ShopCategory.BLOCKS))
                .pos(leftPos + 55, topPos - 20).size(50, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Tools"), b -> switchCat(ShopCategory.TOOLS))
                .pos(leftPos + 110, topPos - 20).size(50, 20).build());

        // 🔹 PAGE BUTTONS
        addRenderableWidget(Button.builder(Component.literal("<"), b -> changePage(-1))
                .pos(leftPos + 10, topPos + 130).size(20, 20).build());

        addRenderableWidget(Button.builder(Component.literal(">"), b -> changePage(1))
                .pos(leftPos + 140, topPos + 130).size(20, 20).build());
    }

    private void switchCat(String cat) {
        menu.category = cat;
        menu.page = 0;
        minecraft.setScreen(new ShopScreen(menu, minecraft.player.getInventory(), title));
    }

    private void changePage(int dir) {
        menu.page += dir;
        if (menu.page < 0) menu.page = 0;
        minecraft.setScreen(new ShopScreen(menu, minecraft.player.getInventory(), title));
    }

    @Override
    protected void renderBg(GuiGraphics g, float p, int mx, int my) {
    }
}