package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.item.MetalusBlockItem;

public class MetalusOre extends MetalusBlock {

    public MetalusOre(String name, Properties properties) {
        super(name, properties, MetalusBlockItem.class);
    }
}
