package com.mattpenguin.metalus.container;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constant.MOD_ID)
public class ModContainerTypes {

    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> register) {
        Metalus.LOGGER.info("Registering container types");
        IForgeRegistry<ContainerType<?>> registry = register.getRegistry();
        Metalus.LOGGER.info("Done registering container types");
    }
}
