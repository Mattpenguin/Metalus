package com.mattpenguin.metalus.net;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Constant.MOD_ID, Constant.NETWORK_CHANNEL))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        int discriminator = 0;
    }

    public static void sendToServer(IMetalusMessage message) {
        HANDLER.sendToServer(message);
    }

    private static void register(int id, Class<IMetalusMessage> messageClass) {
        HANDLER.registerMessage(id, messageClass, IMetalusMessage::write, (pb) -> {
            IMetalusMessage instance = null;
            try {
                instance = messageClass.newInstance();
                instance.read(pb);
            } catch (InstantiationException e) {
                Metalus.LOGGER.error("Instantiation Exception while handling message id " + id);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Metalus.LOGGER.error("Illegal Access Exception while handling message id " + id);
                e.printStackTrace();
            }
            return instance;
        }, IMetalusMessage::enqueue);
    }

}
