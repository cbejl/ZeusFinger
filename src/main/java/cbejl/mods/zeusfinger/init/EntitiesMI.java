package cbejl.mods.zeusfinger.init;

import cbejl.mods.zeusfinger.entity.LightningBall;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface EntitiesMI {

    @RegistryName("lightning_ball")
    EntityType<LightningBall> LIGHTNING_BALL = EntityType.Builder
            .<LightningBall>of(LightningBall::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .build("lightning_ball");
}
