package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.util.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class BroomsEnchantments {

    public static Holder<Enchantment> getHolder(Level level, ResourceKey<Enchantment> enchantment) {
        return level.holderLookup(enchantment.registryKey()).getOrThrow(enchantment);
    }

    public static ResourceKey<Enchantment> createResourceKey(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, path));
    }

    public static ResourceKey<Enchantment> HOVERING = createResourceKey("hovering");
    public static ResourceKey<Enchantment> AIR_SKILLS = createResourceKey("air_skills");
    public static ResourceKey<Enchantment> LAND_SKILLS = createResourceKey("land_skills");
    public static ResourceKey<Enchantment> SEA_BREEZE = createResourceKey("sea_breeze");

    public static void bootstrap(FabricDynamicRegistryProvider.Entries entries) {

        HolderGetter<Item> itemsRegistry = entries.getLookup(Registries.ITEM);

        registerHovering(entries, itemsRegistry);
        registerAirSkills(entries, itemsRegistry);
        registerLandSkills(entries, itemsRegistry);
        registerSeaBreeze(entries, itemsRegistry);

    }

    public static void registerHovering(FabricDynamicRegistryProvider.Entries entries, HolderGetter<Item> itemsRegistry) {

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

        RegistryHelper.registerEnchantment(entries, HOVERING, builder);
    }
    public static void registerAirSkills(FabricDynamicRegistryProvider.Entries entries, HolderGetter<Item> itemsRegistry) {

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

        RegistryHelper.registerEnchantment(entries, AIR_SKILLS, builder);
    }
    public static void registerLandSkills(FabricDynamicRegistryProvider.Entries entries, HolderGetter<Item> itemsRegistry) {

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

        RegistryHelper.registerEnchantment(entries, LAND_SKILLS, builder);
    }
    public static void registerSeaBreeze(FabricDynamicRegistryProvider.Entries entries, HolderGetter<Item> itemsRegistry) {

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

        RegistryHelper.registerEnchantment(entries, SEA_BREEZE, builder);
    }

    private static ResourceKey<Enchantment> key(String pName) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BroomsMod.MODID, pName));
    }
}
