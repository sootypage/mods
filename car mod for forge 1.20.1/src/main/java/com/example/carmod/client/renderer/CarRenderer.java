package com.example.carmod.client.renderer;

import com.example.carmod.entity.CarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CarRenderer extends MobRenderer<CarEntity, CarModel<CarEntity>> {

    public CarRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new CarModel<>(context.bakeLayer(CarModel.LAYER_LOCATION)),
                0.6f
        );
    }

    @Override
    public ResourceLocation getTextureLocation(CarEntity entity) {
        return new ResourceLocation("carmod", "textures/entity/car.png");
    }
}