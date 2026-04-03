package com.example.moneymod.money;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MoneyProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerMoney> MONEY = CapabilityManager.get(new CapabilityToken<>(){});

    private PlayerMoney money = new PlayerMoney();
    private final LazyOptional<PlayerMoney> optional = LazyOptional.of(() -> money);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == MONEY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("balance", money.getBalance());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        money.setBalance(nbt.getInt("balance"));
    }
}