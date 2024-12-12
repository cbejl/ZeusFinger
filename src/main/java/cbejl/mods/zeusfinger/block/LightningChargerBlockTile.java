package cbejl.mods.zeusfinger.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zeith.hammerlib.api.energy.ForgeEnergyHelper;
import org.zeith.hammerlib.tiles.TileSyncableTickable;

import java.util.List;

public class LightningChargerBlockTile extends TileSyncableTickable {

    private final LightningChargerEnergyStorage energy = new LightningChargerEnergyStorage(16000, 0, 16000, 0);
    private final AABB CHECK_BOUNDS = new AABB(
            getBlockPos().offset(-8, -8, -8),
            getBlockPos().offset(8, 8, 8));

    private final String LIGHTNING_TAG = "lightning_charger.already_used";

    private final LazyOptional<IEnergyStorage> energyOptional;

    public LightningChargerBlockTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        energyOptional = LazyOptional.of(() -> energy);
    }

    private static int valueMapper(Vec3 distance) {
        int value = (int) Math.max(Math.abs(distance.x), Math.max(Math.abs(distance.y), Math.abs(distance.z))) - 1;
        return Math.abs(value - 8) * (16000 / 8);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return this.energyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("lightning_charger.energy", this.energy.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        if (nbt.contains("lightning_charger.energy", CompoundTag.TAG_INT)) {
            this.energy.deserializeNBT(nbt.get("lightning_charger.energy"));
        }
        super.load(nbt);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyOptional.invalidate();
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, BlockEntity be) {
        List<Entity> entityList = level.getEntities(null, CHECK_BOUNDS);
        entityList.stream()
                .filter(e -> e instanceof LightningBolt)
                .forEach(e -> {
                    if (this.energy.getEnergyStored() <= this.energy.getMaxEnergyStored()) {
                        if(e.addTag(LIGHTNING_TAG)) {
                            Vec3 distance = pos.getCenter().subtract(e.position());
                            this.energy.addEnergy(valueMapper(distance));
                        }
                    }
                });

        ForgeEnergyHelper.spreadPower(be, 16000, false);
        super.serverTick(level, pos, state, be);
    }

}
