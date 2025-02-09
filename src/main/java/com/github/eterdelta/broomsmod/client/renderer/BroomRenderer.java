package com.github.eterdelta.broomsmod.client.renderer;

import com.github.eterdelta.broomsmod.entity.WoodenBroomEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static org.joml.Math.cos;
import static org.joml.Math.sin;

public abstract class BroomRenderer<T extends WoodenBroomEntity> extends EntityRenderer<T> {
    public BroomRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.6F;
    }

    final Vector3f XP = new Vector3f(1.0f,0.0f,0.0f);
    final Vector3f YP = new Vector3f(0.0f,1.0f,0.0f);
    final Vector3f ZP = new Vector3f(0.0f,0.0f,1.0f);

    public Quaternionf QQA (Quaternionf newQf, Vector3f rotationAxis, float angle, boolean isDegree) {
        if (isDegree) {
            angle *= ((float)Math.PI / 180F);  // 如果角度是度数，转换为弧度
        }

        float sinHalfAngle = sin(angle / 2.0F);
        newQf.x = rotationAxis.x() * sinHalfAngle;
        newQf.y = rotationAxis.y() * sinHalfAngle;
        newQf.z = rotationAxis.z() * sinHalfAngle;
        newQf.w = cos(angle / 2.0F);
        return newQf;
    }

    public Quaternionf rotationDegrees(Vector3f rotationAxis, float angle) {
        Quaternionf newQf = new Quaternionf();
        newQf = QQA(newQf, rotationAxis, angle, true);
        return newQf;
    }


    @Override
    public void render(T broomEntity, float p_113930_, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_113934_) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        poseStack.mulPose(rotationDegrees(YP, -p_113930_));

        float hurtTime = broomEntity.getHurtTime() - partialTicks;

        if (hurtTime > 0.0F) {
            float swingDegrees = Mth.sin(hurtTime) * hurtTime * 2.5F / 10.0F;
            poseStack.mulPose(rotationDegrees(XP, swingDegrees));
            poseStack.mulPose(rotationDegrees(ZP, swingDegrees));
        }

        ResourceLocation broomTexture = this.getTextureLocation(broomEntity);
        EntityModel<T> broomModel = this.getBroomModel();

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(rotationDegrees(YP,180.0F));
        broomModel.setupAnim(broomEntity, partialTicks, 0.0F, broomEntity.tickCount + partialTicks, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = this.getBuffer(broomEntity, broomModel, broomTexture, multiBufferSource);
        broomModel.renderToBuffer(poseStack, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY);
        //broomModel.renderToBuffer(poseStack, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(broomEntity, p_113930_, partialTicks, poseStack, multiBufferSource, p_113934_);
    }

    public VertexConsumer getBuffer(T broomEntity, EntityModel<T> broomModel, ResourceLocation broomTexture, MultiBufferSource multiBufferSource) {
        VertexConsumer buffer;
        if (broomEntity.getItem().isEnchanted()) {
            buffer = VertexMultiConsumer.create(multiBufferSource.getBuffer(RenderType.entityGlint()), multiBufferSource.getBuffer(broomModel.renderType(broomTexture)));
        } else {
            buffer = multiBufferSource.getBuffer(broomModel.renderType(broomTexture));
        }
        return buffer;
    }

    public abstract EntityModel<T> getBroomModel();
}
