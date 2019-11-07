package com.mattpenguin.metalus.block;

import com.mattpenguin.metalus.Metalus;
import com.mattpenguin.metalus.common.Constant;
import com.mattpenguin.metalus.common.IDebugable;
import com.mattpenguin.metalus.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class MetalusBlock extends Block implements IDebugable {

    protected static IProperty[] tempProperties;

    public final String name;
    public final IProperty[] addProperties;

    protected List<BlockRenderLayer> renderLayers = Collections.singletonList(BlockRenderLayer.SOLID);
    protected boolean notNormalBlock;


    public MetalusBlock(String name, Block.Properties blockProperties, @Nullable Class<? extends BlockItem> itemBlock, IProperty... addProperties) {
        super(setTempProperties(blockProperties, addProperties));

        this.name = name;
        this.addProperties = addProperties;
        this.setDefaultState(getInitDefaultState());

        ResourceLocation registryName = createRegistryName();
        setRegistryName(registryName);

        ModBlocks.addBlock(this);

        if (itemBlock != null) {
            try {
                ModItems.addItem(itemBlock.getConstructor(Block.class).newInstance(this));
            } catch (Exception e) {
                Metalus.LOGGER.fatal("ERROR constructing ItemBlock for block: " + name);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean canRenderInLayer(BlockState state, BlockRenderLayer layer) {
        return renderLayers.contains(layer);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return notNormalBlock ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, IBlockReader blockReader, BlockPos blockPos) {
        return notNormalBlock || super.propagatesSkylightDown(blockState, blockReader, blockPos);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(tempProperties);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, BlockState blockState, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, blockPos, blockState, placer, stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1));
    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool) {
        return super.isToolEffective(state, tool);
    }

    @Override
    public String getDebugInfo() {
        return "Root Block Type: MetalusBlock. Debug info not overridden";
    }

    protected static Block.Properties setTempProperties(Properties blockProperties, Object[] additionalProperties) {
        List<IProperty> properties = new ArrayList<>();
        Arrays.stream(additionalProperties).forEach(p -> {
            if (p instanceof IProperty) properties.add((IProperty) p);
            if (p instanceof IProperty[]) properties.addAll(Arrays.asList((IProperty[]) p));
        });
        tempProperties = properties.toArray(new IProperty[0]);
        return blockProperties;
    }

    protected BlockState getInitDefaultState() {
        return this.stateContainer.getBaseState();
    }

    public ResourceLocation createRegistryName() {
        return new ResourceLocation(Constant.MOD_ID, name);
    }

    public MetalusBlock setNotNormal() {
        this.notNormalBlock = true;
        return this;
    }

}
