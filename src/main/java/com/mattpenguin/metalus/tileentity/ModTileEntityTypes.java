package com.mattpenguin.metalus.tileentity;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@ObjectHolder(Constant.MOD_ID)
public class ModTileEntityTypes {

    public static void registerTypes(RegistryEvent.Register<TileEntityType<?>> register) {
        Metalus.LOGGER.info("Registering tile entity types");
        IForgeRegistry<TileEntityType<?>> registry = register.getRegistry();
        Metalus.LOGGER.info("Done registering tile entity types");
    }

    private static void registerSimple(IForgeRegistry<TileEntityType<?>> registry, String name, Supplier<? extends TileEntity> factory, Block... blocks) {
        //noinspection ConstantConditions
        registry.register(TileEntityType.Builder.create(factory, blocks).build(null).setRegistryName(name));
    }
}
