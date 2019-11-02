package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.common.IMetalType;

public enum MetalType implements IMetalType {
    IRON(Constant.Metals.IRON, 5.0F, 6.0F, false),
    GOLD(Constant.Metals.GOLD, 3.0F, 6.0F, false),
    TIN(Constant.Metals.TIN, 0.5F, 0.6F),
    COPPER(Constant.Metals.COPPER, 0.7F, 0.8F);

    private String name;
    private float hardness;
    private float resistance;
    private boolean generate;

    MetalType(String name, float hardness, float resistance) {
        this(name, hardness, resistance, true);
    }

    MetalType(String name, float hardness, float resistance, boolean generate) {
        this.name = name;
        this.hardness = hardness;
        this.resistance = resistance;
        this.generate = generate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getHardness() {
        return this.hardness;
    }

    @Override
    public float getResistance() {
        return this.resistance;
    }

    @Override
    public boolean generateFor() {
        return this.generate;
    }
}
