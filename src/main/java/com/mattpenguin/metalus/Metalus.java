package com.mattpenguin.metalus;

import com.mattpenguin.metalus.block.ModBlocks;
import com.mattpenguin.metalus.client.screen.ModScreens;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.container.ModContainerTypes;
import com.mattpenguin.metalus.item.ModItems;
import com.mattpenguin.metalus.net.PacketHandler;
import com.mattpenguin.metalus.tileentity.ModTileEntityTypes;
import com.mattpenguin.metalus.world.MetalusOreGen;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constant.MOD_ID)
public class Metalus {
    public static final Logger LOGGER = LogManager.getLogger();

    public static Metalus INSTANCE;

    public Metalus() {
        INSTANCE = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up Common");

        PacketHandler.register();

        MetalusOreGen.addOre(ModBlocks.ORE_TIN, 6);
        MetalusOreGen.addOre(ModBlocks.ORE_COPPER, 7);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        LOGGER.info("Setting up Client");

        ModScreens.register();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> register) {
            LOGGER.info("Handling Block Registry Subscription");
            ModBlocks.registerBlocks(register);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> register) {
            LOGGER.info("Handling Item Registry Subscription");
            ModItems.initItemGroup();
            ModItems.registerItems(register);
        }

        @SubscribeEvent
        public static void onTileEntityTypesRegistry(final RegistryEvent.Register<TileEntityType<?>> register) {
            LOGGER.info("Handling Tile Entity Types Registry Subscription");
            ModTileEntityTypes.registerTypes(register);
        }

        @SubscribeEvent
        public static void onContainerTypesRegistry(final RegistryEvent.Register<ContainerType<?>> register) {
            LOGGER.info("Handling Container Types Registry Subscription");
            ModContainerTypes.registerContainerTypes(register);
        }
    }
}
