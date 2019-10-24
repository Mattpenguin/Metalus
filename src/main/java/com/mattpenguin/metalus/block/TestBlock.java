package com.mattpenguin.metalus.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TestBlock extends MetalusBlock {

    static final String REGISTRY_NAME = "test_block";

    public TestBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.STONE));
    }


}
