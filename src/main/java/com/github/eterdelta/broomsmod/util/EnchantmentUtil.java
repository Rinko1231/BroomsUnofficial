package com.github.eterdelta.broomsmod.util;

import com.github.eterdelta.broomsmod.mixin.HolderReferenceAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EnchantmentUtil {
    /**
     * Returning the (possibly event-modified) level.
     *
     * @param level The original level of the enchantment as provided by the Item.
     * @param stack The stack being queried against.
     * @param ench  The enchantment being queried for.
     * @return The new level of the enchantment.
     */
    public static int getEnchantmentLevelSpecific(int level, ItemStack stack, Holder<Enchantment> ench) {
        HolderLookup.RegistryLookup<Enchantment> lookup = ((HolderReferenceAccessor<Enchantment>) ench).getOwner() instanceof HolderLookup.RegistryLookup<Enchantment> rl ? rl : null;
        if (lookup == null) { // Pretty sure this is never null, but I can't *prove* that it isn't.
            return level;
        }

        var enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.set(ench, level);
        return enchantments.getLevel(ench);
    }
}
