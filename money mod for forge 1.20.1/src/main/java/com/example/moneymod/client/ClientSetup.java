package com.example.moneymod.client;

import com.example.moneymod.menu.ModMenus;
import com.example.moneymod.shop.SellScreen;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(modid = "moneymod", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.SELL_MENU.get(), SellScreen::new);
        });
    }
}