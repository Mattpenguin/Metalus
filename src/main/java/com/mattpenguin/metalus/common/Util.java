package com.mattpenguin.metalus.common;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.item.MetalusItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Collection;

public class Util {
    public static <T extends IForgeRegistryEntry<T>> Collection<T> checkNames(Collection<T> collection) {
        if (collection.stream()
                .filter(e -> e.getRegistryName() == null)
                .peek(n -> Metalus.LOGGER.error("Null name for registry entry {} (class {})", n, n.getClass()))
                .count() > 0) System.exit(1);
        return collection;
    }

    @Nullable
    public static Item getOreChunkForBlock(Block b) {
        if (BlockTags.getCollection().getOrCreate(new ResourceLocation("minecraft", "iron_ore")).contains(b)) return MetalusItems.Chunk.iron;
        if (BlockTags.getCollection().getOrCreate(new ResourceLocation("minecraft", "gold_ore")).contains(b)) return MetalusItems.Chunk.gold;
        if (BlockTags.getCollection().getOrCreate(new ResourceLocation("metalus", "ore_copper")).contains(b)) return MetalusItems.Chunk.copper;
        if (BlockTags.getCollection().getOrCreate(new ResourceLocation("metalus", "ore_tin")).contains(b)) return MetalusItems.Chunk.tin;
        return null;
    }
}
