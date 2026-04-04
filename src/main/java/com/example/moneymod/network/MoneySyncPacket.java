package com.example.moneymod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class MoneySyncPacket {

    private final int balance;

    public MoneySyncPacket(int balance) {
        this.balance = balance;
    }

    public static void encode(MoneySyncPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.balance);
    }

    public static MoneySyncPacket decode(FriendlyByteBuf buf) {
        return new MoneySyncPacket(buf.readInt());
    }

    public static void handle(MoneySyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
    }
}