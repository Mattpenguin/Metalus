package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.IDebugable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDebug extends MetalusItem {
    public ItemDebug(String name) {
        super(name);
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getPos();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof IDebugable) {
            Metalus.LOGGER.debug(((IDebugable) state.getBlock()).getDebugInfo());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

}
