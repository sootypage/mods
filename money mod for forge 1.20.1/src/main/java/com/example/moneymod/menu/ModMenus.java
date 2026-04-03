package com.example.moneymod.menu;

import com.example.moneymod.MoneyMod;
import com.example.moneymod.shop.SellMenu;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.*;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoneyMod.MODID);

    public static final RegistryObject<MenuType<SellMenu>> SELL_MENU =
            MENUS.register("sell_menu",
                    () -> new MenuType<>(
                            (IContainerFactory<SellMenu>) SellMenu::new,
                            FeatureFlags.DEFAULT_FLAGS
                    ));
}