package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constant.MOD_ID)
public class ModBlocks {

    @ObjectHolder("test_block")
    public static TestBlock TEST_BLOCK;

    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        Metalus.LOGGER.info("Registering blocks");

        IForgeRegistry<Block> registry = register.getRegistry();

        registry.register(new TestBlock().setRegistryName("test_block"));

        Metalus.LOGGER.info("Done registering blocks");
    }
}
