package com.example.petmod;

import com.example.petmod.registry.ModEntities;
import com.example.petmod.registry.ModBlocks;
import com.example.petmod.registry.ModBlockEntities;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod(PetMod.MOD_ID)
public class PetMod {

    public static final String MOD_ID = "petmod";

    public PetMod() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register everything
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        System.out.println("Pet Mod Loaded Successfully!");
    }
}