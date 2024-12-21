package net.orion.stressedout.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistry {
    public static Capability<StressCapability> STRESS_CAPABILITY;

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(StressCapability.class);
    }
}
