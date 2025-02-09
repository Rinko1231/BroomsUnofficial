package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.entity.WoodenBroomEntity;
import com.github.eterdelta.broomsmod.util.RegistryHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.Registries;


public class BroomsEntities {

    public static final EntityType<WoodenBroomEntity> WOODEN_BROOM = RegistryHelper.registerEntity("wooden_broom",
            EntityType.Builder.<WoodenBroomEntity>of(WoodenBroomEntity::new, MobCategory.MISC) // 显式指定泛型类型
                    .sized(0.7F, 0.6F)
                    .eyeHeight(0.9F * 0.6F)
                    .build(String.valueOf(ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, "wooden_broom")))
    );

    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, path));
    }

    public static void init() {
        BroomsMod.LOGGER.info("BroomsMod Entities registered");
    }
}
