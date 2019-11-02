package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@ObjectHolder(Constant.MOD_ID)
public class ModBlocks {

    @ObjectHolder(Constant.RegistryNames.TEST_BLOCK)
    public static MetalusBlock TEST_BLOCK;

    @ObjectHolder(Constant.RegistryNames.METAL_BLOCK_PREFIX + Constant.Metals.COPPER)
    public static MetalBlock BLOCK_COPPER;

    @ObjectHolder(Constant.RegistryNames.METAL_BLOCK_PREFIX + Constant.Metals.TIN)
    public static MetalBlock BLOCK_TIN;

    @ObjectHolder(Constant.RegistryNames.ORE_PREFIX + Constant.Metals.COPPER)
    public static MetalusOre ORE_COPPER;

    @ObjectHolder(Constant.RegistryNames.ORE_PREFIX + Constant.Metals.TIN)
    public static MetalusOre ORE_TIN;


    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        Metalus.LOGGER.info("Registering blocks");

        IForgeRegistry<Block> registry = register.getRegistry();

        registry.register(new TestBlock().setRegistryName(TestBlock.REGISTRY_NAME));

        Arrays.stream(MetalType.values()).filter(MetalType::generateFor).forEach(m -> {
            registry.register(new MetalusOre(
                    Block.Properties.create(Material.ROCK)
                            .hardnessAndResistance(2F, 3F), 0, 0)
                    .setRegistryName(Constant.RegistryNames.ORE_PREFIX + m.getName())
            );
            registry.register(new MetalBlock(m)
                    .setRegistryName(Constant.RegistryNames.METAL_BLOCK_PREFIX + m.getName()));

        });

        Metalus.LOGGER.info("Done registering blocks");
    }
}
