package com.example.petmod.registry;

import com.example.petmod.PetMod;
import com.example.petmod.entity.PetDog;
import com.example.petmod.entity.PetCat;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PetMod.MOD_ID);

    public static final RegistryObject<EntityType<PetDog>> DOG =
            ENTITIES.register("dog",
                    () -> EntityType.Builder.of(PetDog::new, MobCategory.CREATURE)
                            .sized(0.6f, 0.8f)
                            .build("dog"));

    public static final RegistryObject<EntityType<PetCat>> CAT =
            ENTITIES.register("cat",
                    () -> EntityType.Builder.of(PetCat::new, MobCategory.CREATURE)
                            .sized(0.5f, 0.7f)
                            .build("cat"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}