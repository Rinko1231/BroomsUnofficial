package com.github.eterdelta.broomsmod.handler;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.client.renderer.WoodenBroomRenderer;
import com.github.eterdelta.broomsmod.client.renderer.model.WoodenBroomModel;
import com.github.eterdelta.broomsmod.registry.BroomsEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@EventBusSubscriber(modid = BroomsMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BroomsEntities.WOODEN_BROOM.get(), WoodenBroomRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WoodenBroomModel.LAYER_LOCATION, WoodenBroomModel::createBodyLayer);
    }
}
