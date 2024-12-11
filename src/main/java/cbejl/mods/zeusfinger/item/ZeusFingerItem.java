package cbejl.mods.zeusfinger.item;

import cbejl.mods.zeusfinger.entity.LightningBall;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ZeusFingerItem extends Item {
    private static final int DURATION_TO_SHOOT = 40;

    public ZeusFingerItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingDuration) {
        int duration = getUseDuration(itemStack) - remainingDuration;
        if (DURATION_TO_SHOOT - duration > 0) return;

        if (!level.isClientSide) {
            itemStack.hurtAndBreak(1, entity, (e) -> {
                e.broadcastBreakEvent(entity.getUsedItemHand());
            });

            LightningBall lightningBall = new LightningBall(level, entity);
            lightningBall.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 1f, 0.5F);
            level.addFreshEntity(lightningBall);

        }

        level.playSound(entity, BlockPos.containing(entity.getEyePosition())
                , SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.PLAYERS, 0.5f, 0.5f);
        if (entity instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int remainingDuration) {
        if (getUseDuration(itemStack) - remainingDuration < DURATION_TO_SHOOT && remainingDuration % 10 == 0) {
            entity.playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 0.4f, 0.3f);
        }
        if (getUseDuration(itemStack) - remainingDuration == DURATION_TO_SHOOT) {
            entity.playSound(SoundEvents.AMETHYST_CLUSTER_BREAK, 1f, 3f);
        }
        super.onUseTick(level, entity, itemStack, remainingDuration);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!player.isUsingItem()) player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }
}
