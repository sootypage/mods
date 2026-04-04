package com.example.moneymod.shop;

import net.minecraft.world.item.ItemStack;

public class ShopListing {

    public ItemStack item;
    public int price;
    public String owner;
    public String category;

    public ShopListing(ItemStack item, int price, String owner, String category) {
        this.item = item;
        this.price = price;
        this.owner = owner;
        this.category = category;
    }
}