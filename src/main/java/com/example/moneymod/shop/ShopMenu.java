package com.example.moneymod.shop;

import com.example.moneymod.menu.ModMenus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ShopMenu extends AbstractContainerMenu {

    public String category = ShopCategory.ALL;
    public int page = 0;

    public ShopMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenus.SHOP_MENU.get(), id);

        build(inv.player);
    }

    private void build(Player player) {

        List<ShopListing> items = ShopPager.getPage(
                ShopStorage.LISTINGS,
                category,
                page
        );

        int index = 0;

        for (ShopListing listing : items) {

            addSlot(new Slot(new net.minecraft.world.SimpleContainer(listing.item.copy()), 0,
                    10 + (index % 7) * 18,
                    20 + (index / 7) * 18) {

                @Override
                public boolean mayPickup(Player player) {
                    return false;
                }

                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });

            index++;
        }
    }

    @Override
    public void clicked(int slotId, int dragType, ClickType type, Player player) {

        if (!(player instanceof ServerPlayer sp)) return;

        List<ShopListing> items = ShopPager.getPage(
                ShopStorage.LISTINGS,
                category,
                page
        );

        if (slotId < 0 || slotId >= items.size()) return;

        ShopListing listing = items.get(slotId);

        sp.addItem(listing.item.copy());
        ShopStorage.LISTINGS.remove(listing);

        sp.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("Bought for $" + listing.price)
        );
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