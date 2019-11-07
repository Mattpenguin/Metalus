package com.mattpenguin.metalus.tileentity;

import com.mattpenguin.metalus.block.interfaces.IBlockstateProvider;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class MetalusTileEntity extends TileEntity implements IBlockstateProvider {

    private final Set<LazyOptional<?>> capabilities = new HashSet<>();

    public MetalusTileEntity(TileEntityType<? extends TileEntity> type) {
        super(type);
    }

    @Override
    public void read(CompoundNBT tagCompound) {
        super.read(tagCompound);
        this.readCustomNBT(tagCompound, false);
    }

    public abstract void readCustomNBT(CompoundNBT tagCompound, boolean descPacket);

    @Override
    public CompoundNBT write(CompoundNBT tagCompound) {
        super.write(tagCompound);
        this.writeCustomNBT(tagCompound, false);
        return tagCompound;
    }

    public abstract void writeCustomNBT(CompoundNBT tagCompound, boolean descPacket);

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tagCompound = new CompoundNBT();
        this.writeCustomNBT(tagCompound, true);
        return new SUpdateTileEntityPacket(this.pos, 3, tagCompound);
    }

    @Override
    public CompoundNBT getUpdateTag(){
        CompoundNBT tagCompound = super.getUpdateTag();
        writeCustomNBT(tagCompound, true);
        return tagCompound;
    }

    @Override
    public void onDataPacket(NetworkManager manager, SUpdateTileEntityPacket packet) {
        this.readCustomNBT(packet.getNbtCompound(), true);
    }

    @Override
    public BlockState getState() {
        return getBlockState();
    }

    @Override
    public void setState(BlockState state) {
        getNonnullWorld().setBlockState(pos, state);
    }

    public void recieveMessageFromClient(CompoundNBT tagCompound) {}
    public void recieveMessageFromServer(CompoundNBT tagCompound) {}

    @Override
    public boolean receiveClientEvent(int id, int type){
        if (id == 0 || id == 255){
            markContainingBlockForUpdate(null);
            return true;
        }
        if (id == 254) {
            BlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            return true;
        }
        return super.receiveClientEvent(id, type);
    }

    private void markContainingBlockForUpdate(@Nullable BlockState newState) {
        markBlockForUpdate(getPos(), newState);
    }

    private void markBlockForUpdate(BlockPos pos, @Nullable BlockState newState) {
        BlockState state = world.getBlockState(pos);
        if (newState == null) newState = state;
        world.notifyBlockUpdate(pos, state, newState, 3);
        world.notifyNeighborsOfStateChange(pos, newState.getBlock());
    }

    protected <T> LazyOptional<T> registerConstantCapability(T val) {
        return registerCapability(() -> val);
    }

    protected <T> LazyOptional<T> registerCapability(NonNullSupplier<T> capability) {
        return registerCapability(LazyOptional.of(capability));
    }

    protected <T> LazyOptional<T> registerCapability(LazyOptional<T> capability) {
        capabilities.add(capability);
        return capability;
    }

    protected <T> void unregisterCapability(LazyOptional<T> capability) {
        capability.invalidate();
        capabilities.remove(capability);
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        return super.getCapability(capability, side);
    }

    @Override
    public void remove() {
        super.remove();
        capabilities.stream()
                .filter(LazyOptional::isPresent)
                .forEach(LazyOptional::invalidate);
    }

    @Nonnull
    public World getNonnullWorld() {
        return Objects.requireNonNull(super.getWorld());
    }
}
