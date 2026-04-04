package com.example.petmod.registry;

import com.example.petmod.PetMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.food.FoodProperties;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PetMod.MOD_ID);

    // 🐶 DOG ITEMS

    public static final RegistryObject<Item> DOG_BONE = ITEMS.register("dog_bone",
            () -> new Item(new Properties().stacksTo(16)));

    public static final RegistryObject<Item> DOG_TREAT = ITEMS.register("dog_treat",
            () -> new Item(new Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .build()
                    )));

    public static final RegistryObject<Item> DOG_FOOD = ITEMS.register("dog_food",
            () -> new Item(new Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.6f)
                            .build()
                    )));

    // 🐱 CAT ITEMS

    public static final RegistryObject<Item> CAT_TREAT = ITEMS.register("cat_treat",
            () -> new Item(new Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .build()
                    )));

    public static final RegistryObject<Item> CAT_FOOD = ITEMS.register("cat_food",
            () -> new Item(new Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.5f)
                            .build()
                    )));

    // 🐟 Taming Item (Fish already exists in Minecraft, but we can add custom too later)

    // 🔧 Register method (IMPORTANT)
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}