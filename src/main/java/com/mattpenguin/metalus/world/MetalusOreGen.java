package com.mattpenguin.metalus.world;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class MetalusOreGen {
    public static void addOre(Block ore, int veinSize) {
        ForgeRegistries.BIOMES.getValues()
                .stream()
                .filter(biome -> biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NETHER)
                .forEach(biome -> biome.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Biome.createDecoratedFeature(
                                Feature.ORE,
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        ore.getDefaultState(),
                                        veinSize
                                ),
                                Placement.COUNT_RANGE,
                                new CountRangeConfig(15, 40, 0, 128)
                        )
                ));
    }
}
