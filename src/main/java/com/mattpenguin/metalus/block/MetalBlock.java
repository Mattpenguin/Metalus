package com.mattpenguin.metalus.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MetalBlock extends MetalusBlock {

    public MetalBlock(MetalType type) {
        super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(type.getHardness(), type.getResistance()));
    }

}
