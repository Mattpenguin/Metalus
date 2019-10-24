package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.common.IDebugable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class MetalusBlock extends Block implements IDebugable {

    private static final Properties DEFAULT_PROPERTIES = Block.Properties
            .create(Material.IRON)
            .hardnessAndResistance(0.8F)
            .sound(SoundType.METAL);

    public MetalusBlock() {
        this(DEFAULT_PROPERTIES);
    }

    public MetalusBlock(Properties p) {
        super(p);
    }

    @Override
    public String getDebugInfo() {
        return "Root Block Type: MetalusBlock. Debug info not overridden";
    }

}
