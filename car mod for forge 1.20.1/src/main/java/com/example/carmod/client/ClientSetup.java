package com.example.carmod.client;

import com.example.carmod.CarMod;
import com.example.carmod.client.renderer.CarModel;
import com.example.carmod.client.renderer.CarRenderer;
import com.example.carmod.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CarMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientSetup {

    // 🚗 REGISTER ENTITY RENDERER
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        System.out.println("[CAR MOD] Registering Car Renderer");

        event.registerEntityRenderer(
                ModEntities.CAR.get(),
                CarRenderer::new
        );
    }

    // 🧱 REGISTER MODEL LAYER (THIS FIXES INVISIBLE MODELS)
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        System.out.println("[CAR MOD] Registering Car Model Layer");

        event.registerLayerDefinition(
                CarModel.LAYER_LOCATION,
                CarModel::createBodyLayer
        );
    }
}