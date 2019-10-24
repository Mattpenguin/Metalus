package com.mattpenguin.metalus.block;

public enum MetalType {
    IRON("iron", 0.5F),
    GOLD("gold", 0.3F),
    TIN("tin", 0.2F),
    COPPER("copper", 0.3F);

    private String name;
    private float hardness;

    MetalType(String name, float hardness) {
        this.name = name;
        this.hardness = hardness;
    }

    public String getName() { return this.name; }
    public float getHardness() { return this.hardness; }
}
