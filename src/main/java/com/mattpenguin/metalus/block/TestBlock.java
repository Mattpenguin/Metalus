package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.common.IDebugable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TestBlock extends Block implements IDebugable {

    public TestBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.STONE));
    }


}
