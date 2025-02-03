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
import net.neoforged.neoforge.common.conditions.ICondition;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BroomsEnchantments {

    public static Holder<Enchantment> getHolder(Level level, ResourceKey<Enchantment> enchantment) {
        return level.holderLookup(enchantment.registryKey()).getOrThrow(enchantment);
    }

    public static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> enchantment, Enchantment.Builder builder) {
        context.register(enchantment, builder.build(enchantment.location()));
    }

    public static ResourceKey<Enchantment> createResourceKey(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, path));
    }

    public static final Map<ResourceKey<?>, List<ICondition>> conditions = new HashMap<>();
    public static ResourceKey<Enchantment> HOVERING = createResourceKey("hovering");
    public static ResourceKey<Enchantment> AIR_SKILLS = createResourceKey("air_skills");
    public static ResourceKey<Enchantment> LAND_SKILLS = createResourceKey("land_skills");
    public static ResourceKey<Enchantment> SEA_BREEZE = createResourceKey("sea_breeze");

    public static void bootstrap(BootstrapContext<Enchantment> context) {

        HolderGetter<Item> itemsRegistry = context.lookup(Registries.ITEM);

        registerHovering(context, itemsRegistry);
        registerAirSkills(context, itemsRegistry);
        registerLandSkills(context, itemsRegistry);
        registerSeaBreeze(context, itemsRegistry);

    }

    private static void registerHovering(BootstrapContext<Enchantment> context, HolderGetter<Item> itemsRegistry) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                itemsRegistry.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                5,
                4,
                Enchantment.dynamicCost(1,20),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.ANY
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, HOVERING, builder);
    }
    private static void registerAirSkills(BootstrapContext<Enchantment> context, HolderGetter<Item> itemsRegistry) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                itemsRegistry.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                10,
                3,
                Enchantment.dynamicCost(1,15),
                Enchantment.constantCost(60),
                1,
                EquipmentSlotGroup.ANY
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, AIR_SKILLS, builder);
    }
    private static void registerLandSkills(BootstrapContext<Enchantment> context, HolderGetter<Item> itemsRegistry) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                itemsRegistry.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                10,
                3,
                Enchantment.dynamicCost(1,15),
                Enchantment.constantCost(60),
                1,
                EquipmentSlotGroup.ANY
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, LAND_SKILLS, builder);
    }
    private static void registerSeaBreeze(BootstrapContext<Enchantment> context, HolderGetter<Item> itemsRegistry) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                itemsRegistry.getOrThrow(BroomsTags.Items.WOODEN_BROOM),
                2,
                1,
                Enchantment.dynamicCost(1,40),
                //Enchantment.constantCost(40),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.ANY
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, SEA_BREEZE, builder);
    }

    private static ResourceKey<Enchantment> key(String pName) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, pName));
    }

}
