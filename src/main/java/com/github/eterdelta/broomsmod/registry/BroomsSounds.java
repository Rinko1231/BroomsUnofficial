package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.util.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;


public class BroomsSounds {
    public static final SoundEvent BROOM_DESTROY = createEvent("entity.wooden_broom.destroy");
    public static final SoundEvent BROOM_FALL = createEvent("entity.wooden_broom.fall");

    private static SoundEvent createEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, sound);
        return RegistryHelper.registerSound(sound, SoundEvent.createVariableRangeEvent(name));
    }

    public static void init() {
        BroomsMod.LOGGER.info("BroomsMod SoundEvent registered");
    }
}
