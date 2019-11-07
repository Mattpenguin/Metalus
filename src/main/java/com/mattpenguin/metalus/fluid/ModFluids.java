package com.mattpenguin.metalus.fluid;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.common.RLUtils;
import com.mattpenguin.metalus.common.Util;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constant.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFluids {
    private static List<Fluid> fluids = new ArrayList<>();

    public static void addFluid(Fluid fluid) {
        fluids.add(fluid);
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
        Metalus.LOGGER.info("Registering Fluids");

        Util.checkNames(fluids).forEach(event.getRegistry()::register);

        Metalus.LOGGER.info("Done Registering Fluids");
    }

    public static void construct() {
        MetalusFluids.Metal.moltenCopper = new MetalFluid(
                Constant.RegistryNames.MOLTEN_METAL_PREFIX + Constant.Metals.COPPER,
                RLUtils.getFluidLocation(Constant.MOD_ID, Constant.RegistryNames.MOLTEN_METAL_PREFIX + Constant.Metals.COPPER, "_still"),
                RLUtils.getFluidLocation(Constant.MOD_ID, Constant.RegistryNames.MOLTEN_METAL_PREFIX + Constant.Metals.COPPER, "_flow"));
    }
}
