package cbejl.mods.zeusfinger.init;

import cbejl.mods.zeusfinger.ZeusFinger;
import cbejl.mods.zeusfinger.entity.LightningBallLayer;
import cbejl.mods.zeusfinger.entity.LightningBallModel;
import cbejl.mods.zeusfinger.entity.LightningBallRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ZeusFinger.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LightningBallLayer.LIGHTNING_BALL_LAYER, LightningBallModel::createLayer);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntitiesMI.LIGHTNING_BALL, LightningBallRenderer::new);
    }
}
