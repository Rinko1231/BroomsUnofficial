package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class BroomsTags {
    public static class Items {
        public static final TagKey<Item> WOODEN_BROOM = tag("broom");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, name));
        }
        private static TagKey<Enchantment> create(String name)
        {
            return TagKey.create(Registries.ENCHANTMENT, cr1ate(name));
        }
        public static ResourceLocation cr1ate(String path) {
            return ResourceLocation.fromNamespaceAndPath(com.github.eterdelta.broomsmod.BroomsMod.MODID, path);
        }
    }
}
