package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BroomsTags {



    public static class Items {
        public static final TagKey<Item> WOODEN_BROOM = tag("broom");



        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, name));
        }


    }



}
