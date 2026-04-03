package com.example.moneymod.shop;

import com.google.gson.*;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.nio.file.*;
import java.util.*;

public class ShopStorage {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FMLPaths.CONFIGDIR.get().resolve("moneymod/shop.json");

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());

            List<JsonObject> data = new ArrayList<>();

            for (ShopItem item : ShopManager.ITEMS) {
                JsonObject obj = new JsonObject();

                obj.addProperty("item", ForgeRegistries.ITEMS.getKey(item.item.getItem()).toString());
                obj.addProperty("count", item.item.getCount());
                obj.addProperty("price", item.price);
                obj.addProperty("owner", item.owner);
                obj.addProperty("temporary", item.temporary);
                obj.addProperty("expireTime", item.expireTime);

                data.add(obj);
            }

            Files.writeString(FILE, GSON.toJson(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            if (!Files.exists(FILE)) return;

            String json = Files.readString(FILE);
            JsonArray array = JsonParser.parseString(json).getAsJsonArray();

            ShopManager.ITEMS.clear();

            for (JsonElement el : array) {
                JsonObject obj = el.getAsJsonObject();

                Item item = ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation(obj.get("item").getAsString())
                );

                int count = obj.get("count").getAsInt();
                int price = obj.get("price").getAsInt();
                String owner = obj.get("owner").getAsString();
                boolean temp = obj.has("temporary") && obj.get("temporary").getAsBoolean();
                long expire = obj.has("expireTime") ? obj.get("expireTime").getAsLong() : 0;

                if (item != null) {
                    ShopManager.ITEMS.add(
                            new ShopItem(new ItemStack(item, count), price, owner, temp, expire)
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}