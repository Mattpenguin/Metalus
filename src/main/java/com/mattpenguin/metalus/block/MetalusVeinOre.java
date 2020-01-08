package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.item.MetalusBlockItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Optional;

public class MetalusVeinOre extends MetalusBlock {

    public static final IntegerProperty ORE_COUNT = Constant.Properties.ORE_COUNT;

    public MetalusVeinOre(String name, Properties properties) {
        super(name, properties, MetalusBlockItem.class);
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getOreCountProperty(), 0));
    }

    public IntegerProperty getOreCountProperty () {
        return ORE_COUNT;
    }

    protected int getOreCount(BlockState state) {
        return state.get(this.getOreCountProperty());
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {

        //Check if the player is in creative. If so, remove the block immediately
        if (player != null && player.abilities.isCreativeMode) {
            world.removeBlock(pos, false);
            return true;
        }

        //Ensure we can harvest the block
        if (willHarvest) {
            this.onBlockHarvested(world, pos, state, player);
            int count = state.get(this.getOreCountProperty());

        }



        if (--count == 0) {

        }

        return true;
    }

    public static boolean breakBlock(IWorld world, BlockState state, PlayerEntity player) {
        if (this.getOreCount(state))
            this.rem
    }
}
