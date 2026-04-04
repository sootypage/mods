package com.example.moneymod.shop;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SellPrices {

    private static final Map<net.minecraft.world.item.Item, Integer> PRICES = new HashMap<>();

    static {
        PRICES.put(Items.DIAMOND, 50);
        PRICES.put(Items.IRON_INGOT, 10);
        PRICES.put(Items.GOLD_INGOT, 20);
        PRICES.put(Items.COBBLESTONE, 1);
    }

    public static int getPrice(ItemStack stack) {
        return PRICES.getOrDefault(stack.getItem(), 0);
    }
}