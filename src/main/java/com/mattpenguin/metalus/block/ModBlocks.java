package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constant.MOD_ID)
public class ModBlocks {

    @ObjectHolder(Constant.RegistryNames.TEST_BLOCK)
    public static MetalusBlock TEST_BLOCK;

    @ObjectHolder(Constant.RegistryNames.METAL_BLOCK_PREFIX + Constant.Metals.COPPER)
    public static MetalBlock BLOCK_COPPER;

    @ObjectHolder(Constant.RegistryNames.METAL_BLOCK_PREFIX + Constant.Metals.TIN)
    public static MetalBlock BLOCK_TIN;


    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        Metalus.LOGGER.info("Registering blocks");

        IForgeRegistry<Block> registry = register.getRegistry();

        registry.register(new TestBlock().setRegistryName(TestBlock.REGISTRY_NAME));

        for (MetalType metalType : MetalType.values()) {
            registry.register(new MetalBlock(metalType)
                    .setRegistryName(Constant.RegistryNames.METAL_BLOCK_PREFIX + metalType.getName()));
        }

        Metalus.LOGGER.info("Done registering blocks");
    }
}
