package com.mattpenguin.metalus.common;

public interface IMetalTier {

    IMetalType getMaterial();
    int getBaseDurability();
    float getBaseSpeed();
    float getBaseAttack();
    int getBaseHarvestLevel();
    int getBaseEnchantibility();

}
