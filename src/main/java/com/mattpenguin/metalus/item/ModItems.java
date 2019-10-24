package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.block.MetalType;
import com.mattpenguin.metalus.block.ModBlocks;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder(Constant.MOD_ID)
public class ModItems {

    @ObjectHolder("debug_tool")
    public static ItemDebug ITEM_DEBUG;

    public static ItemGroup METALUS_ITEM_GROUP;

    public static void registerItems(RegistryEvent.Register<Item> register) {
        Metalus.LOGGER.info("Registering items");

        IForgeRegistry<Item> registry = register.getRegistry();

        //Items
        registry.register(new ItemDebug(new Item.Properties().group(METALUS_ITEM_GROUP)).setRegistryName("debug_tool"));

        //Item Blocks
        registerItemForBlock(registry, ModBlocks.TEST_BLOCK);
        registerItemForBlock(registry, ModBlocks.BLOCK_TIN);
        registerItemForBlock(registry, ModBlocks.BLOCK_COPPER);

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
            @Nonnull
            public ItemStack createIcon() {
                return new ItemStack(ITEM_DEBUG);
            }
        };
    }
}
