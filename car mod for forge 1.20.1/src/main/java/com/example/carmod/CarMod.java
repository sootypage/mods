package com.example.carmod;

import com.example.carmod.entity.ModEntities;
import com.example.carmod.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CarMod.MODID)
public class CarMod {

    public static final String MODID = "carmod";

    public CarMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.ENTITY_TYPES.register(bus);
        ModItems.ITEMS.register(bus);
    }
}