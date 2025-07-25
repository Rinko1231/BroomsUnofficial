package com.github.eterdelta.broomsmod.mixin;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBakery.class)
public abstract class ModelLoaderMixin {

    @Shadow
    protected abstract void loadSpecialItemModelAndDependencies(ModelResourceLocation id);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", ordinal = 1, shift = At.Shift.AFTER))
    public void add(CallbackInfo ci) {
        this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, "succuba_hat")));
        this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, "succuba_hat_fake")));
        this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(com.github.eterdelta.broomsmod.BroomsMod.MODID, "wooden_broom_in_hand")));
    }

}