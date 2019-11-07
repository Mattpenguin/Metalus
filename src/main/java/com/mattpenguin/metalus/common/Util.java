package com.mattpenguin.metalus.common;

import com.mattpenguin.metalus.Metalus;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collection;

public class Util {
    public static <T extends IForgeRegistryEntry<T>> Collection<T> checkNames(Collection<T> collection) {
        if (collection.stream()
                .filter(e -> e.getRegistryName() == null)
                .peek(n -> Metalus.LOGGER.error("Null name for registry entry {} (class {})", n, n.getClass()))
                .count() > 0) System.exit(1);
        return collection;
    }
}
