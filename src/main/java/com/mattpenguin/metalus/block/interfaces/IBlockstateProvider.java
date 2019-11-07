package com.mattpenguin.metalus.block.interfaces;

import net.minecraft.block.BlockState;

public interface IBlockstateProvider {
    BlockState getState();
    void setState(BlockState state);
}
