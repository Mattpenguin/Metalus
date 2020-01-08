package com.mattpenguin.metalus.handler;

import com.mattpenguin.metalus.block.MetalusVeinOre;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Handlers {
    @SubscribeEvent
    public static void onBlockBreakEvent(BlockEvent.BreakEvent event){
        if (event.getState().getBlock() instanceof MetalusVeinOre) {

        }
    }
}
