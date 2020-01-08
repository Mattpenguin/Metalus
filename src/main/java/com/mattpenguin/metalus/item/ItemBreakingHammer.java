package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.block.MetalusOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;

public class ItemBreakingHammer extends MetalusItem {

    public ItemBreakingHammer(String name) {
        super(name, new Item.Properties().group(Metalus.ITEM_GROUP).maxStackSize(1));
    }

    @Override
    public boolean canHarvestBlock(BlockState blockState) {
        Block block = blockState.getBlock();
        boolean willHarvest = super.canHarvestBlock(blockState) ||
                block instanceof OreBlock ||
                block instanceof MetalusOre;
        Metalus.LOGGER.debug("Will the breaking hammer harvest this block? " + willHarvest);
        return willHarvest;
    }
}
