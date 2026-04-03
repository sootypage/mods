package com.example.moneymod.shop;

import com.example.moneymod.money.MoneyProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class ShopMenu extends AbstractContainerMenu {

    public ShopMenu(int id, Inventory inv) {
        super(MenuType.GENERIC_9x6, id);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {

        if (id < ShopManager.ITEMS.size()) {

            ShopItem shopItem = ShopManager.ITEMS.get(id);

            if (player instanceof net.minecraft.server.level.ServerPlayer sp) {

                sp.getCapability(MoneyProvider.MONEY).ifPresent(money -> {

                    if (money.getBalance() >= shopItem.price) {

                        money.addMoney(-shopItem.price);
                        sp.addItem(shopItem.item.copy());

                        // 💰 PAY SELLER
                        boolean paid = false;

                        for (var p : sp.server.getPlayerList().getPlayers()) {
                            if (p.getName().getString().equals(shopItem.owner)) {

                                p.getCapability(MoneyProvider.MONEY).ifPresent(m -> {
                                    m.addMoney(shopItem.price);
                                });

                                p.sendSystemMessage(
                                        net.minecraft.network.chat.Component.literal(
                                                "Your item sold for $" + shopItem.price
                                        )
                                );

                                paid = true;
                                break;
                            }
                        }

                        // 📬 OFFLINE PAYMENT
                        if (!paid) {
                            com.example.moneymod.util.OfflinePayments.add(shopItem.owner, shopItem.price);
                        }

                        ShopManager.ITEMS.remove(id);
                        ShopStorage.save();

                        sp.sendSystemMessage(
                                net.minecraft.network.chat.Component.literal(
                                        "Bought for $" + shopItem.price
                                )
                        );

                    } else {
                        sp.sendSystemMessage(
                                net.minecraft.network.chat.Component.literal("Not enough money")
                        );
                    }
                });
            }

            return true;
        }

        return false;
    }
}