package com.mattpenguin.metalus.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMetalusMessage {
    void read(PacketBuffer buf);
    void write(PacketBuffer buf);
    default void enqueue(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> handle(context));
    }
    void handle(NetworkEvent.Context context);
}
