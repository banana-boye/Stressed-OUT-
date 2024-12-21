package net.orion.stressedout;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.orion.stressedout.capability.CapabilityRegistry;
import net.orion.stressedout.capability.StressCapabilityProvider;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(stressedOutMod.MODID)
public class stressedOutMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "stressedout";
    private static final Logger LOGGER = LogUtils.getLogger();


    public stressedOutMod(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.addGenericListener(Player.class, StressCapabilityProvider::attachCapabilities);

        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(CapabilityRegistry.STRESS_CAPABILITY).ifPresent(oldCap -> {
            event.getEntity().getCapability(CapabilityRegistry.STRESS_CAPABILITY).ifPresent(newCap -> {
                newCap.setStress(oldCap.getStress());
            });
        });
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
