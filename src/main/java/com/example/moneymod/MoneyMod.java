package com.example.moneymod;

import com.example.moneymod.item.ModItems;
import com.example.moneymod.network.ModNetwork;
import com.example.moneymod.command.*;
import com.example.moneymod.menu.ModMenus;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(MoneyMod.MODID)
@Mod.EventBusSubscriber(modid = MoneyMod.MODID)
public class MoneyMod {

    public static final String MODID = "moneymod";

    public MoneyMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModNetwork.register();
        ModMenus.MENUS.register(bus);

        MinecraftForge.EVENT_BUS.register(new com.example.moneymod.money.CoinConverter());
    }

    @SubscribeEvent
    public static void onCommands(RegisterCommandsEvent event) {
        MoneyCommands.register(event.getDispatcher());
        BankCommands.register(event.getDispatcher());
        ShopCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event) {
        com.example.moneymod.util.OfflinePayments.load();
    }

    // 📬 GIVE OFFLINE MONEY
    @SubscribeEvent
    public static void onPlayerJoin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent e) {

        if (e.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {

            int money = com.example.moneymod.util.OfflinePayments.claim(player.getName().getString());

            if (money > 0) {
                player.getCapability(com.example.moneymod.money.MoneyProvider.MONEY).ifPresent(m -> {
                    m.addMoney(money);
                });

                player.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal("You received $" + money + " from sales")
                );
            }
        }
    }
}