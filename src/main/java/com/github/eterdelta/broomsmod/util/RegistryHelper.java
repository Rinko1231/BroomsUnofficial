package com.github.eterdelta.broomsmod.util;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class RegistryHelper {
    public static Item registerItem(String id, Item item) {
        // Create the identifier for the item.
        ResourceLocation itemId = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, id);

        // Register the item.
        Item registeredItem = Registry.register(BuiltInRegistries.ITEM, itemId, item);

        // Return the registered item!
        return registeredItem;
    }

    public static SoundEvent registerSound(String id, SoundEvent soundEvent) {
        ResourceLocation soundId = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, soundId, soundEvent);
    }

    public static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> entityType) {
        ResourceLocation entityId = ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, id);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, entityId, entityType);
    }

    public static void registerEnchantment(FabricDynamicRegistryProvider.Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.location()), resourceConditions);
    }
}
