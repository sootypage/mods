package com.example.carmod.item;

import com.example.carmod.entity.CarEntity;
import com.example.carmod.entity.ModEntities;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class CarItem extends Item {

    public CarItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();

        if (!level.isClientSide) {
            CarEntity car = new CarEntity(ModEntities.CAR.get(), level);
            car.setPos(context.getClickedPos().above().getCenter());
            level.addFreshEntity(car);
        }

        return InteractionResult.SUCCESS;
    }
}