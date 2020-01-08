package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.block.MetalType;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.common.Util;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constant.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    private static List<Item> items = new ArrayList<>();

    public static void addItem(Item item){
        items.add(item);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Metalus.LOGGER.info("Registering items");

        Util.checkNames(items).forEach(event.getRegistry()::register);

        Metalus.LOGGER.info("Done registering items");
    }

    public static void construct() {
        MetalusItems.Misc.debugTool = new ItemDebug(Constant.RegistryNames.DEBUG_TOOL);
        MetalusItems.Misc.debugOre = new ItemOreGenDebug(Constant.RegistryNames.DEBUG_ORE);


        MetalusItems.Ingots.ingotCopper = new MetalusItem(Constant.RegistryNames.METAL_INGOT_PREFIX + MetalType.COPPER.getName());
        MetalusItems.Ingots.ingotTin = new MetalusItem(Constant.RegistryNames.METAL_INGOT_PREFIX + MetalType.TIN.getName());

        MetalusItems.Tool.breakingHammer = new ItemBreakingHammer(Constant.RegistryNames.BREAKING_HAMMER);

        MetalusItems.Chunk.copper = new MetalusItem(Constant.RegistryNames.CHUNK_PREFIX + MetalType.COPPER.getName());
        MetalusItems.Chunk.tin = new MetalusItem(Constant.RegistryNames.CHUNK_PREFIX + MetalType.TIN.getName());
        MetalusItems.Chunk.gold = new MetalusItem(Constant.RegistryNames.CHUNK_PREFIX + MetalType.IRON.getName());
        MetalusItems.Chunk.copper = new MetalusItem(Constant.RegistryNames.CHUNK_PREFIX + MetalType.GOLD.getName());
    }
}
