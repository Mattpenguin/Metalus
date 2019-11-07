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

    public static void constuct() {
        MetalusItems.Misc.debugTool = new ItemDebug(Constant.RegistryNames.DEBUG_TOOL);

        MetalusItems.Ingots.ingotCopper = new MetalusItem(Constant.RegistryNames.METAL_INGOT_PREFIX + MetalType.COPPER.getName());
        MetalusItems.Ingots.ingotTin = new MetalusItem(Constant.RegistryNames.METAL_INGOT_PREFIX + MetalType.TIN.getName());
    }
}
