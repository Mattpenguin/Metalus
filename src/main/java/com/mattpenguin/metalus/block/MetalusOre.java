package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.block.interfaces.IOre;
import com.mattpenguin.metalus.item.MetalusBlockItem;

public class MetalusOre extends MetalusBlock implements IOre {

    public MetalusOre(String name, Properties properties) {
        super(name, properties, MetalusBlockItem.class);
    }
}
