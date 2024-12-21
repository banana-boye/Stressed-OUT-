package net.orion.stressedout.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class StressCapabilityProvider implements ICapabilityProvider{
    public static final ResourceLocation CAPABILITY_ID = new ResourceLocation("stressedout", "stress");

    public final StressCapabilityImpl stressCapability = new StressCapabilityImpl();
    public final LazyOptional<StressCapability> optional = LazyOptional.of(() -> stressCapability);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        return cap == CapabilityRegistry.STRESS_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    public static void attachCapabilities(AttachCapabilitiesEvent<Player> event) {
        event.addCapability(CAPABILITY_ID, new StressCapabilityProvider());
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("Stress", stressCapability.getStress());
    }

    public void loadNBTData(CompoundTag nbt) {
        stressCapability.setStress(nbt.getInt("Stress"));
    }
}
