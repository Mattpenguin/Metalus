package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.common.Util;
import com.mattpenguin.metalus.item.MetalusBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constant.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    private static List<Block> blocks = new ArrayList<>();


    public static void addBlock(Block block) {
        blocks.add(block);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        Metalus.LOGGER.info("Registering blocks");

        Util.checkNames(blocks).stream().peek(b -> Metalus.LOGGER.debug("$#$Registering {} (class {})", b, b.getClass())).forEach(event.getRegistry()::register);

        Metalus.LOGGER.info("Done registering blocks");
    }

    public static void construct() {
        MetalusBlocks.MetalBlock.copperBlock = new MetalBlock(MetalType.COPPER);
        MetalusBlocks.MetalBlock.tinBlock = new MetalBlock(MetalType.TIN);

        Block.Properties defaultOreProperties = Block.Properties.create(Material.ROCK).hardnessAndResistance(3, 15);
        MetalusBlocks.Ores.copperOre = new MetalusOre(Constant.RegistryNames.ORE_PREFIX + MetalType.COPPER.getName(), defaultOreProperties);
        MetalusBlocks.Ores.tinOre = new MetalusOre(Constant.RegistryNames.ORE_PREFIX + MetalType.TIN.getName(), defaultOreProperties);

        MetalusBlocks.Misc.testBlock = new MetalusBlock(Constant.RegistryNames.TEST_BLOCK, defaultOreProperties, MetalusBlockItem.class);

        Block.Properties defaultFluidProperties = Block.Properties.create(Material.LAVA).doesNotBlockMovement().hardnessAndResistance(100F).noDrops();
    }
}
