package com.github.eterdelta.broomsmod;

import com.github.eterdelta.broomsmod.client.renderer.WoodenBroomRenderer;
import com.github.eterdelta.broomsmod.client.renderer.model.WoodenBroomModel;
import com.github.eterdelta.broomsmod.registry.BroomsEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BroomsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BroomsEntities.WOODEN_BROOM, WoodenBroomRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(WoodenBroomModel.LAYER_LOCATION, WoodenBroomModel::createBodyLayer);
    }
}
