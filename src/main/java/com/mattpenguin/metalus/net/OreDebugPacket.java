package com.mattpenguin.metalus.net;

import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.item.ItemOreGenDebug;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OreDebugPacket {
    public static void encode(OreDebugPacket msg, PacketBuffer buf) {}
    public static OreDebugPacket decode(PacketBuffer buf) {
        return new OreDebugPacket();
    }

    public static class Handler {
        public static void handle(OreDebugPacket msg, Supplier<NetworkEvent.Context> ctx){
            ctx.get().enqueueWork(() -> {
                PlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    IWorld world = player.world;
                    BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
                    ItemOreGenDebug.generate(world, Constant.RANDOM, pos, 10);
                }
            });
        }
    }
}
