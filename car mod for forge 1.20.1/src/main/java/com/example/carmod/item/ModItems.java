package com.example.carmod.item;

import com.example.carmod.CarMod;
import com.example.carmod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CarMod.MODID);

    public static final RegistryObject<Item> CAR_SPAWN_EGG =
            ITEMS.register("car_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.CAR,
                            0x222222,
                            0xff0000,
                            new Item.Properties()
                    ));
}