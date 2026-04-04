package com.example.moneymod.network;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraft.resources.ResourceLocation;
import com.example.moneymod.MoneyMod;

public class ModNetwork {

    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MoneyMod.MODID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    public static void register() {
        CHANNEL.registerMessage(0,
                MoneySyncPacket.class,
                MoneySyncPacket::encode,
                MoneySyncPacket::decode,
                MoneySyncPacket::handle);
    }
}