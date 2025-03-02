package com.github.eterdelta.broomsmod.mixin;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.registry.BroomsItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    public BakedModel use(
            BakedModel model,
            ItemStack stack,
            ItemDisplayContext renderMode,
            boolean leftHanded,
            PoseStack matrices,
            MultiBufferSource multiBufferSource
    ) {
        boolean blm = renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.FIXED || renderMode == ItemDisplayContext.GROUND;
        if (!blm) {
            if (stack.is(BroomsItems.SUCCUBA_HAT)) {
                return ((ItemRendererAccessor) this).getModels().getModelManager().getModel(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, "succuba_hat")));
            }
            if (stack.is(BroomsItems.WOODEN_BROOM) && !(renderMode == ItemDisplayContext.HEAD)) {
                return ((ItemRendererAccessor) this).getModels().getModelManager().getModel(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(com.github.eterdelta.broomsmod.BroomsMod.MODID, "wooden_broom_in_hand")));
            }
        }
        return model;
    }
}
