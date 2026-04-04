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

public class PetCat extends TamableAnimal {

    private int hunger = 100;
    private int tickTimer = 0;

    protected PetCat(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        tickTimer++;

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

        // 🐟 Tame with fish
        if (!this.isTame() && item.getItem() == Items.COD) {
            if (!level().isClientSide) {
                this.tame(player);
                item.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        // 🍗 Feed cat
        if (this.isTame() && item.getItem() == ModItems.CAT_FOOD.get()) {
            hunger = Math.min(100, hunger + 25);
            this.heal(4.0F);
            item.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }
}