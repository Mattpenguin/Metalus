package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constant.MOD_ID)
public class ModItems {

    public static ItemGroup METALUS_ITEM_GROUP;

    public static void registerItems(RegistryEvent.Register<Item> register) {
        Metalus.LOGGER.info("Registering items");
        IForgeRegistry<Item> registry = register.getRegistry();
        Metalus.LOGGER.info("Done registering items");
    }

    private static void registerItemForBlock(IForgeRegistry<Item> registry, Block... blocks) {
        for (Block block : blocks) {
            Item i = new BlockItem(block, new Item.Properties().group(METALUS_ITEM_GROUP));
            i.setRegistryName(block.getRegistryName()); // TODO Resolve warning
            registry.register(i);
        }
    }

    public static void initItemGroup() {
        Metalus.LOGGER.info("Initializing item group");
        METALUS_ITEM_GROUP = new ItemGroup(Constant.MOD_ID) {
            @Override
            public ItemStack createIcon() {
                return null; // TODO Resolve
            }
        };
    }
}
