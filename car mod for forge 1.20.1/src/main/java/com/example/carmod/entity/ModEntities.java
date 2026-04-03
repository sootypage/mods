package com.example.carmod.entity;

import com.example.carmod.CarMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CarMod.MODID);

    public static final RegistryObject<EntityType<CarEntity>> CAR =
            ENTITY_TYPES.register("car",
                    () -> EntityType.Builder.of(CarEntity::new, MobCategory.MISC)
                            .sized(1.5f, 1.0f)
                            .build("car"));
}