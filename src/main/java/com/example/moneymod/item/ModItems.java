package com.example.moneymod.item;

import com.example.moneymod.MoneyMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoneyMod.MODID);

    public static final RegistryObject<Item> COPPER_COIN =
            ITEMS.register("copper_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_COIN =
            ITEMS.register("silver_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLD_COIN =
            ITEMS.register("gold_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_COIN =
            ITEMS.register("platinum_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_COIN =
            ITEMS.register("diamond_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_COIN =
            ITEMS.register("ruby_coin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYTHIC_COIN =
            ITEMS.register("mythic_coin", () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}