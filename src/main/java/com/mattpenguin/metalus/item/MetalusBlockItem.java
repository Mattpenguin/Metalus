package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetalusBlockItem extends BlockItem {

    @SuppressWarnings("ConstantConditions")
    public MetalusBlockItem (Block b) {
        super (b, new Item.Properties().group(Metalus.ITEM_GROUP));
        setRegistryName(b.getRegistryName());
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return getBlock().getTranslationKey();
    }
}
