package com.example.moneymod.shop;

import net.minecraft.world.item.ItemStack;

public class ShopItem {
    public ItemStack item;
    public int price;
    public String owner;

    // NEW
    public boolean temporary;
    public long expireTime;

    public ShopItem(ItemStack item, int price, String owner, boolean temporary, long expireTime) {
        this.item = item;
        this.price = price;
        this.owner = owner;
        this.temporary = temporary;
        this.expireTime = expireTime;
    }
}