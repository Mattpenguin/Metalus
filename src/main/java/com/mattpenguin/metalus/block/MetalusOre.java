package com.mattpenguin.metalus.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class MetalusOre extends OreBlock {
    private int minExp;
    private int maxExp;

    public MetalusOre(Properties properties, int minExp, int maxExp) {
        super(properties);
        this.minExp = minExp;
        this.maxExp = maxExp;
    }

    @Override
    protected int func_220281_a(Random rand) {
        return MathHelper.nextInt(rand, this.minExp, this.maxExp);
    }

    @Override
    public void spawnAdditionalDrops(BlockState state, World world, BlockPos pos, ItemStack item) {
        super.spawnAdditionalDrops(state, world, pos, item);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silk) {
        return silk == 0 ? this.func_220281_a(RANDOM) : 0;
    }
}
