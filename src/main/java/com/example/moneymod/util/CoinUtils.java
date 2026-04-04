package com.example.moneymod.util;

import com.example.moneymod.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoinUtils {

    public static int getValue(Item item) {
        if (item == ModItems.COPPER_COIN.get()) return 1;
        if (item == ModItems.SILVER_COIN.get()) return 10;
        if (item == ModItems.GOLD_COIN.get()) return 100;
        return 0;
    }

    public static void autoConvert(Player player) {
        int total = 0;

        for (ItemStack stack : player.getInventory().items) {
            total += getValue(stack.getItem()) * stack.getCount();
        }

        for (ItemStack stack : player.getInventory().items) {
            if (getValue(stack.getItem()) > 0) {
                stack.setCount(0);
            }
        }

        int gold = total / 100;
        int silver = (total % 100) / 10;
        int copper = total % 10;

        if (gold > 0)
            player.addItem(new ItemStack(ModItems.GOLD_COIN.get(), gold));

        if (silver > 0)
            player.addItem(new ItemStack(ModItems.SILVER_COIN.get(), silver));

        if (copper > 0)
            player.addItem(new ItemStack(ModItems.COPPER_COIN.get(), copper));
    }
}