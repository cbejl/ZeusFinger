package cbejl.mods.zeusfinger.entity;

import cbejl.mods.zeusfinger.ZeusFinger;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightningBallRenderer extends EntityRenderer<LightningBall> {
    public static final ResourceLocation LIGHTNING_BALL_LOCATION = new ResourceLocation(ZeusFinger.MOD_ID, "textures/entity/lightning_ball.png");
    private final LightningBallModel model;

    public LightningBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LightningBallModel(context.bakeLayer(LightningBallLayer.LIGHTNING_BALL_LAYER));
    }

    public void render(LightningBall pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0f, -0.75f, 0f);

        VertexConsumer vertexconsumer =
                ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, false);

        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    public ResourceLocation getTextureLocation(LightningBall lightningBall) {
        return LIGHTNING_BALL_LOCATION;
    }
}
