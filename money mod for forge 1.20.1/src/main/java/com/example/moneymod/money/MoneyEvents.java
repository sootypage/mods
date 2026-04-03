package com.example.moneymod.money;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.example.moneymod.MoneyMod;

@Mod.EventBusSubscriber(modid = MoneyMod.MODID)
public class MoneyEvents {

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof Player player) {
            event.addCapability(new ResourceLocation(MoneyMod.MODID, "money"), new MoneyProvider());
        }
    }

    @SubscribeEvent
    public static void clone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(MoneyProvider.MONEY).ifPresent(oldStore -> {
            event.getEntity().getCapability(MoneyProvider.MONEY).ifPresent(newStore -> {
                newStore.setBalance(oldStore.getBalance());
            });
        });
    }
}