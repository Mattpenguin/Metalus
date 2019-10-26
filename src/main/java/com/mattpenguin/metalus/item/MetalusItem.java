package com.mattpenguin.metalus.item;

import net.minecraft.item.Item;

public class MetalusItem extends Item {

    private static final Properties DEFAULT_PROPERTIES = new Item.Properties()
            .maxStackSize(1);

    public MetalusItem() {
        this(DEFAULT_PROPERTIES);
    }

    public MetalusItem(Properties properties) {
        super(properties.group(ModItems.METALUS_ITEM_GROUP));
    }

}
