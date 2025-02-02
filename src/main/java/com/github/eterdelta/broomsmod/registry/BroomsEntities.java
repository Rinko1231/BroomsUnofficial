package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.entity.WoodenBroomEntity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import java.util.function.Supplier;


public class BroomsEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, BroomsMod.MODID);

    public static final Supplier<EntityType<WoodenBroomEntity>> WOODEN_BROOM = ENTITIES.register("wooden_broom", () -> EntityType.Builder.of(WoodenBroomEntity::new, MobCategory.MISC)
            .sized(0.7F, 0.6F).eyeHeight(0.9F * 0.6F).build(prefix("wooden_broom")));

    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, path));
    }
}
