package com.mattpenguin.metalus.net;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Constant.MOD_ID, Constant.NETWORK_CHANNEL))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static int index;

    public static void register() {
        registerMessage(OreDebugPacket.class, OreDebugPacket::encode, OreDebugPacket::decode, OreDebugPacket.Handler::handle);
    }

    private static <M> void registerMessage(Class<M> type, BiConsumer<M, PacketBuffer> encoder, Function<PacketBuffer, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> consumer) {
        HANDLER.registerMessage(index++, type, encoder, decoder, consumer);
    }

    public static void sendToServer(Object msg) {
        HANDLER.sendToServer(msg);
    }

}
