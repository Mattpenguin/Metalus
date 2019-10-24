package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constant.MOD_ID)
public class ModBlocks {

    @ObjectHolder(TestBlock.REGISTRY_NAME)
    public static MetalusBlock TEST_BLOCK;

    @ObjectHolder("block_metal_copper")
    public static MetalBlock BLOCK_COPPER;

    @ObjectHolder("block_metal_tin")
    public static MetalBlock BLOCK_TIN;


    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        Metalus.LOGGER.info("Registering blocks");

        IForgeRegistry<Block> registry = register.getRegistry();

        registry.register(new TestBlock().setRegistryName(TestBlock.REGISTRY_NAME));

        for(MetalType metalType : MetalType.values()) {
            registry.register(new MetalBlock(metalType).setRegistryName("block_metal_"+metalType.getName()));
        }

        Metalus.LOGGER.info("Done registering blocks");
    }
}
