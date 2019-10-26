package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.block.MetalType;

public class MetalItem extends MetalusItem {
    private MetalType metalType;

    public MetalItem (Properties properties, MetalType metalType) {
        super(properties);
        this.metalType = metalType;
    }

    public MetalType getMetalType() {
        return this.metalType;
    }
}
