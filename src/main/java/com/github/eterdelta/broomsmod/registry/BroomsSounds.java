package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BroomsSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, BroomsMod.MODID);

    public static final Holder<SoundEvent> BROOM_DESTROY = createHolderEvent("entity.wooden_broom.destroy");
    public static final Holder<SoundEvent> BROOM_FALL = createHolderEvent("entity.wooden_broom.fall");

    private static Supplier<SoundEvent> createEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }

    private static Holder<SoundEvent> createHolderEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }



}
