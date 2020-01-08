package com.mattpenguin.metalus.common;

import net.minecraft.state.IntegerProperty;

import java.util.Random;

public class Constant {
    public static final String MOD_ID = "metalus";
    public static final String NETWORK_CHANNEL = "net_metalus_main";

    public static final Random RANDOM = new Random();

    public static class Properties {
        public static final IntegerProperty ORE_COUNT = IntegerProperty.create("ore_count", 0, Short.MAX_VALUE);
    }

    public static class ResourceRoot {
        public static final String FLUID = "block/fluid/";
    }

    public static class RegistryNames {
        public static final String TEST_BLOCK = "test_block";
        public static final String METAL_BLOCK_PREFIX = "block_metal_";
        public static final String ORE_PREFIX = "ore_";

        public static final String CHUNK_PREFIX = "ore_chunk_";

        public static final String BREAKING_HAMMER = "breaking_hammer";

        public static final String DEBUG_TOOL = "debug_tool";
        public static final String DEBUG_ORE = "ore_debug";

        public static final String METAL_INGOT_PREFIX = "ingot_metal_";

        public static final String MOLTEN_METAL_PREFIX = "fluid_molten_";
    }

    public static class Metals {
        public static final String TIN = "tin";
        public static final String COPPER = "copper";
        public static final String GOLD = "gold";
        public static final String IRON = "iron";
    }
}
