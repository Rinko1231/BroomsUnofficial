package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BroomsEnchantments {


    public static final Map<ResourceKey<?>, List<ICondition>> conditions = new HashMap<>();
    public static Holder<Enchantment> HOVERING;
    public static Holder<Enchantment> AIR_SKILLS;
    public static Holder<Enchantment> LAND_SKILLS;
    public static Holder<Enchantment> SEA_BREEZE;

    private static ResourceKey<Enchantment> key(String pName) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, pName));
    }

    private static Holder<Enchantment> register(
            BootstrapContext<Enchantment> pContext,
            ResourceKey<Enchantment> pKey,
            Enchantment.Builder pBuilder
    ) {
        // 将条件存储到 Map
        conditions.put(pKey, List.of(new ModLoadedCondition(BroomsMod.MODID)));

        // 注册附魔并返回其 Holder
        return pContext.register(pKey, pBuilder.build(pKey.location()));
    }

    public static void bootstrap(BootstrapContext<Enchantment> pContext) {
        HolderGetter<Enchantment> enchantmentHolderGetter = pContext.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> itemHolderGetter = pContext.lookup(Registries.ITEM);

        register(
                pContext,
                key("air_skills"),
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                                3,
                                3,
                                Enchantment.dynamicCost(30, 10),
                                Enchantment.dynamicCost(80, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
        );
        register(
                pContext,
                key("land_skills"),
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                                3,
                                3,
                                Enchantment.dynamicCost(30, 10),
                                Enchantment.dynamicCost(80, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
        );
        register(
                pContext,
                key("hovering"),
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                                3,
                                3,
                                Enchantment.dynamicCost(30, 10),
                                Enchantment.dynamicCost(80, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
        );
        register(
                pContext,
                key("sea_breeze"),
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                                3,
                                3,
                                Enchantment.dynamicCost(30, 10),
                                Enchantment.dynamicCost(80, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
        );


    }


}
