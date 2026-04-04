package com.example.moneymod.command;

import com.example.moneymod.shop.*;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ListItemCommand {

    public static void register(CommandDispatcher dispatcher) {

        dispatcher.register(Commands.literal("listitem")
                .then(Commands.argument("price", IntegerArgumentType.integer())
                        .executes(ctx -> {

                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            int price = IntegerArgumentType.getInteger(ctx, "price");

                            ItemStack held = player.getMainHandItem();
                            if (held.isEmpty()) return 0;

                            ShopStorage.add(new ShopListing(
                                    held.copy(),
                                    price,
                                    player.getName().getString(),
                                    ShopCategory.BLOCKS
                            ));

                            player.getInventory().removeItem(player.getInventory().selected, 1);

                            player.sendSystemMessage(Component.literal("Item listed for $" + price));

                            return 1;
                        })));
    }
}