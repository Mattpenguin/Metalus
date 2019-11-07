package com.mattpenguin.metalus.common;

import net.minecraft.util.ResourceLocation;

public class RLUtils {
    public static ResourceLocation getResourceLocation(String modId, String path) {
        return new ResourceLocation(modId + ":" + path);
    }

    public static ResourceLocation getFluidLocation(String modId, String fluidName) {
        return getFluidLocation(modId, fluidName, "");
    }

    public static ResourceLocation getFluidLocation(String modId, String fluidName, String suffix) {
        return getResourceLocation(modId, Constant.ResourceRoot.FLUID + fluidName + suffix);
    }
}
