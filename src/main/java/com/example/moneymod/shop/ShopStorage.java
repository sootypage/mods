package com.example.moneymod.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopStorage {

    public static final List<ShopListing> LISTINGS = new ArrayList<>();

    public static void add(ShopListing listing) {
        LISTINGS.add(listing);
    }

    public static void remove(ShopListing listing) {
        LISTINGS.remove(listing);
    }
}