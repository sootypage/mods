package com.example.moneymod.shop;

import com.example.moneymod.menu.ModMenus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleContainer;

public class SellMenu extends AbstractContainerMenu {

    public ItemStack item = ItemStack.EMPTY;
    public int price = 100;

    public SellMenu(int id, Inventory inv) {
        this(id, inv, null);
    }

    public SellMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenus.SELL_MENU.get(), id);

        // 📦 slot container (simple 1-slot sell box)
        addSlot(new Slot(new SimpleContainer(1), 0, 80, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return true;
            }

            @Override
            public void set(ItemStack stack) {
                super.set(stack);
                item = stack.copy();
            }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}