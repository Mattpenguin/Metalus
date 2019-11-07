package com.mattpenguin.metalus.item;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetalusItem extends Item {

    public String name;
    private int burnTime = -1;

    public MetalusItem(String name) {
        this(name, new Properties());
    }

    public MetalusItem(String name, Properties properties) {
        super(properties.group(Metalus.ITEM_GROUP));
        this.name = name;
        setRegistryName(Constant.MOD_ID, name);
        ModItems.addItem(this);
    }

    @Override
    public int getBurnTime(ItemStack stack) {
        return burnTime;
    }

    @Override
    public boolean hasCustomProperties() {
        return true;
    }

    public MetalusItem setBurnTime(int burnTime) {
        this.burnTime = burnTime;
        return this;
    }
}
