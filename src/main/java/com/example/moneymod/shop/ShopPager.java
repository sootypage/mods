package com.example.moneymod.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopPager {

    public static final int PAGE_SIZE = 28;

    public static List<ShopListing> getPage(List<ShopListing> all, String category, int page) {

        List<ShopListing> filtered = new ArrayList<>();

        for (ShopListing l : all) {
            if (category.equals(ShopCategory.ALL) || l.category.equals(category)) {
                filtered.add(l);
            }
        }

        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, filtered.size());

        if (start >= filtered.size()) return new ArrayList<>();

        return filtered.subList(start, end);
    }
}