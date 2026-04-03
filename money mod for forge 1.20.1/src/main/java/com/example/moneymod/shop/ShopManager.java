package com.example.moneymod.shop;

import java.util.*;

public class ShopManager {
    public static final List<ShopItem> ITEMS = new ArrayList<>();

    public static void tick() {
        long now = System.currentTimeMillis();

        Iterator<ShopItem> it = ITEMS.iterator();

        while (it.hasNext()) {
            ShopItem item = it.next();

            if (item.temporary && now > item.expireTime) {
                it.remove(); // remove expired
            }
        }
    }
}