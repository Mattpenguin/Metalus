package com.mattpenguin.metalus.fluid;

import com.mattpenguin.metalus.common.Constant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class MetalusFluid extends FlowingFluid {
    protected final String name;
    protected final ResourceLocation still;
    protected final ResourceLocation flow;

    protected Fluid flowing;
    protected Fluid source;


    private final Consumer<FluidAttributes.Builder> attributes;

    private final Supplier<Block> block;

    public MetalusFluid(String name, ResourceLocation still, ResourceLocation flow) {
        this(name, still, flow, null);
    }

    public MetalusFluid(String name, ResourceLocation still, ResourceLocation flow, @Nullable Consumer<FluidAttributes.Builder> attributes) {
        this(name, still, flow, attributes, null);
    }

    public MetalusFluid(String name, ResourceLocation still, ResourceLocation flow, @Nullable Consumer<FluidAttributes.Builder> attributes, @Nullable Supplier<Block> block) {
        this(name, still, flow, attributes, block, true);
    }

    public MetalusFluid(String name, ResourceLocation still, ResourceLocation flow, @Nullable Consumer<FluidAttributes.Builder> attributes, @Nullable Supplier<Block> block, boolean isSource) {
        this.name = name;
        this.still = still;
        this.flow = flow;
        this.attributes = attributes;
        this.block = block;
        ModFluids.addFluid(this);
        if (isSource) {
            source = this;
            flowing = createFlowingVariant();
            setRegistryName(Constant.MOD_ID, name);
        } else {
            flowing = this;
            setRegistryName(Constant.MOD_ID, name + "_flowing");
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
        super.fillStateContainer(builder);
        if (flowing == this) {
            builder.add(LEVEL_1_8);
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public Item getFilledBucket() {
        return Items.AIR;
    }

    @Override
    protected boolean func_215665_a(IFluidState state, IBlockReader blockReader, BlockPos blockPos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && fluid != source && fluid != flowing;
    }

    @Override
    public int getTickRate(IWorldReader worldReader) {
        return 5;
    }

    @Override
    protected float getExplosionResistance() {
        return 100F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        if (block == null) return Blocks.AIR.getDefaultState();
        else return block.get().getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isSource(IFluidState state) {
        return state.getFluid() == source;
    }

    @Override
    public int getLevel(IFluidState state) {
        return state.get(LEVEL_1_8);
    }

    @Override
    protected FluidAttributes createAttributes() {
        FluidAttributes.Builder builder = FluidAttributes.builder(still, flow);
        if (attributes != null) attributes.accept(builder);
        return builder.build(this);
    }

    @Override
    public Fluid getFlowingFluid() {
        return this.flowing;
    }

    @Override
    public Fluid getStillFluid() {
        return this.source;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld world, BlockPos pos, BlockState state) {

    }

    @Override
    protected int getSlopeFindDistance(IWorldReader worldReader) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader worldReader) {
        return 1;
    }

    protected Fluid createFlowingVariant() {
        MetalusFluid r = new MetalusFluid(name, still, flow, attributes, block, false);
        r.flowing = this;
        return r;
    }

    public static final IDataSerializer<Optional<FluidStack>> OPTIONAL_FLUID_STACK = new IDataSerializer<Optional<FluidStack>>() {
        @Override
        public void write(PacketBuffer buf, Optional<FluidStack> value) {
            buf.writeBoolean(value.isPresent());
            value.ifPresent(fluidStack -> buf.writeCompoundTag(fluidStack.writeToNBT(new CompoundNBT())));
        }

        @Override
        public Optional<FluidStack> read(PacketBuffer buf) {
            FluidStack fluidStack = !buf.readBoolean() ? null : FluidStack.loadFluidStackFromNBT(buf.readCompoundTag());
            return Optional.ofNullable(fluidStack);
        }

        @Override
        public DataParameter<Optional<FluidStack>> createKey(int id) {
            return new DataParameter<>(id, this);
        }

        @Override
        public Optional<FluidStack> copyValue(Optional<FluidStack> value) {
            return value.map(FluidStack::copy);
        }
    };
}
