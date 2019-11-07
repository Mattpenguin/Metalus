package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.item.MetalusBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MetalBlock extends MetalusBlock {

    public MetalBlock(MetalType type) {
        super(Constant.RegistryNames.METAL_BLOCK_PREFIX + type.getName(), Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(type.getHardness(), type.getResistance()), MetalusBlockItem.class);
    }

}
