package com.example.moneymod.money;

import com.example.moneymod.item.ModItems;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CoinConverter {

    private int tick = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (!(event.player instanceof ServerPlayer player)) return;

        tick++;
        if (tick < 20) return; // run every second
        tick = 0;

        convert(player);
    }

    private void convert(ServerPlayer player) {

        convertTier(player, ModItems.COPPER_COIN.get(), ModItems.SILVER_COIN.get(), 10);
        convertTier(player, ModItems.SILVER_COIN.get(), ModItems.GOLD_COIN.get(), 10);
        convertTier(player, ModItems.GOLD_COIN.get(), ModItems.PLATINUM_COIN.get(), 5);
        convertTier(player, ModItems.PLATINUM_COIN.get(), ModItems.DIAMOND_COIN.get(), 2);
        convertTier(player, ModItems.DIAMOND_COIN.get(), ModItems.RUBY_COIN.get(), 5);
        convertTier(player, ModItems.RUBY_COIN.get(), ModItems.MYTHIC_COIN.get(), 2);
    }

    private void convertTier(ServerPlayer player, Item from, Item to, int rate) {

        int total = 0;

        // 🔍 count total coins
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == from) {
                total += stack.getCount();
            }
        }

        if (total < rate) return;

        int toConvert = total / rate;
        int needed = toConvert * rate;

        int remainingToRemove = needed;

        // 🔥 ONLY REMOVE WHAT WE NEED
        for (int i = 0; i < player.getInventory().items.size(); i++) {

            ItemStack stack = player.getInventory().items.get(i);

            if (stack.getItem() == from) {

                int remove = Math.min(stack.getCount(), remainingToRemove);

                stack.shrink(remove);
                remainingToRemove -= remove;

                if (remainingToRemove <= 0) break;
            }
        }

        // 💰 GIVE NEW COINS SAFELY
        ItemStack reward = new ItemStack(to, toConvert);

        if (!player.addItem(reward)) {
            // ❗ if inventory full → drop safely
            player.drop(reward, false);
        }
    }
}