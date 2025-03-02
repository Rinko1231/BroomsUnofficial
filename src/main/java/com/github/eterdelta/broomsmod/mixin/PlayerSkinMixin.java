package com.github.eterdelta.broomsmod.mixin;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.registry.BroomsGamerule;
import com.github.eterdelta.broomsmod.registry.BroomsItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class PlayerSkinMixin {

    @Unique
    private static final ResourceLocation ELAINA = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, "textures/entity/elaina.png");

    @Unique
    private static final PlayerSkin PLAYER_SKIN = new PlayerSkin(ELAINA, null, null, null, PlayerSkin.Model.SLIM, true);

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void skin(CallbackInfoReturnable<PlayerSkin> cir) {
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
        if (
                player.level().getGameRules().getBoolean(BroomsGamerule.ELAINA) && player.level().isClientSide
                || player.getItemBySlot(EquipmentSlot.HEAD).is(BroomsItems.SUCCUBA_HAT)
        ) {
            cir.setReturnValue(PLAYER_SKIN);
        }
    }
}
