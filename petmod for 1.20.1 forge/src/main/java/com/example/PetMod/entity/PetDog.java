package com.example.petmod.entity;

import com.example.petmod.registry.ModItems;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PetDog extends TamableAnimal {

    private int hunger = 100;
    private int tickTimer = 0;

    protected PetDog(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        tickTimer++;

        // Hunger decreases every 5 seconds
        if (tickTimer >= 100) {
            tickTimer = 0;
            hunger--;

            if (hunger <= 0) {
                this.hurt(this.damageSources().starve(), 1.0F);
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        // 🦴 Tame with dog bone
        if (!this.isTame() && item.getItem() == ModItems.DOG_BONE.get()) {
            if (!level().isClientSide) {
                this.tame(player);
                item.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        // 🍖 Feed dog
        if (this.isTame() && item.getItem() == ModItems.DOG_FOOD.get()) {
            this.hunger = Math.min(100, hunger + 30);
            this.heal(5.0F);
            item.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }
}