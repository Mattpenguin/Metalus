package com.mattpenguin.metalus;

import com.mattpenguin.metalus.block.MetalusBlocks;
import com.mattpenguin.metalus.block.ModBlocks;
import com.mattpenguin.metalus.client.screen.ModScreens;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.fluid.ModFluids;
import com.mattpenguin.metalus.item.MetalusItems;
import com.mattpenguin.metalus.item.ModItems;
import com.mattpenguin.metalus.net.PacketHandler;
import com.mattpenguin.metalus.world.MetalusOreGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constant.MOD_ID)
public class Metalus {
    public static final Logger LOGGER = LogManager.getLogger();

    public static Metalus INSTANCE;

    public Metalus() {
        INSTANCE = this;

        IEventBus loadBus = FMLJavaModLoadingContext.get().getModEventBus();
        loadBus.addListener(this::setupCommon);
        loadBus.addListener(this::fingerprintViolation);

        constructAll();
    }

    public void constructAll() {
        LOGGER.info("Constructing mod objects");
        ModItems.constuct();
        ModBlocks.construct();
        ModFluids.construct();
        LOGGER.info("Done constructing objects");
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up Common");

        PacketHandler.register();

        MetalusOreGen.addOre(MetalusBlocks.Ores.tinOre, 6);
        MetalusOreGen.addOre(MetalusBlocks.Ores.copperOre, 7);
        LOGGER.info("Done setting up common");
    }

    private void setupClient(final FMLClientSetupEvent event) {
        LOGGER.info("Setting up Client");

        ModScreens.register();
    }

    public void fingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Fingerprint violation for Metalus. This is *NOT* and official build. It is recommended that you do not use the existing copy of the Metalus Mod.");
    }

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constant.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MetalusItems.Ingots.ingotCopper);
        }
    };
}
