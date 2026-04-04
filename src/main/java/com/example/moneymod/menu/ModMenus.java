package com.example.moneymod.menu;

import com.example.moneymod.MoneyMod;
import com.example.moneymod.shop.ShopMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoneyMod.MODID);

    public static final RegistryObject<MenuType<ShopMenu>> SHOP_MENU =
            MENUS.register("shop_menu",
                    () -> IForgeMenuType.create(ShopMenu::new));
}