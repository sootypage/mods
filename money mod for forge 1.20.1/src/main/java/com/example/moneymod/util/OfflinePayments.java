package com.example.moneymod.util;

import com.google.gson.*;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.*;
import java.util.*;

public class OfflinePayments {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FMLPaths.CONFIGDIR.get().resolve("moneymod/payments.json");

    public static Map<String, Integer> PAYMENTS = new HashMap<>();

    public static void add(String player, int amount) {
        PAYMENTS.put(player, PAYMENTS.getOrDefault(player, 0) + amount);
        save();
    }

    public static int claim(String player) {
        int amount = PAYMENTS.getOrDefault(player, 0);
        PAYMENTS.remove(player);
        save();
        return amount;
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(PAYMENTS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            if (!Files.exists(FILE)) return;
            String json = Files.readString(FILE);
            PAYMENTS = GSON.fromJson(json, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}