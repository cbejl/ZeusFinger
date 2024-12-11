package cbejl.mods.zeusfinger.entity;

import cbejl.mods.zeusfinger.init.EntitiesMI;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class LightningBall extends AbstractArrow {

    public LightningBall(EntityType<? extends LightningBall> entityType, Level level) {
        super(entityType, level);
    }

    public LightningBall(Level level, LivingEntity entity) {
        super(EntitiesMI.LIGHTNING_BALL, entity, level);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        if(tickCount > 1200) {
            this.discard();
        }

        super.tick();
    }

    @Override
    public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
        return true;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (!this.level().isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level(), this.blockPosition(), MobSpawnType.TRIGGERED);
            this.discard();
        }
        super.onHit(hitResult);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.AMETHYST_CLUSTER_HIT;
    }
}
