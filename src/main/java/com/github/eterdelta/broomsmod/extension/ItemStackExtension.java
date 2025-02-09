package com.github.eterdelta.broomsmod.extension;

import com.github.eterdelta.broomsmod.util.EnchantmentUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public interface ItemStackExtension extends FabricItemStack {
    /**
     * Gets the gameplay level of the target enchantment on this stack.
     * <p>
     * Use in place of {@link net.minecraft.world.item.enchantment.EnchantmentHelper#getItemEnchantmentLevel(Holder, ItemStack)} for gameplay logic.
     * <p>
     * Use {@link net.minecraft.world.item.enchantment.EnchantmentHelper#getEnchantmentsForCrafting} and {@link net.minecraft.world.item.enchantment.EnchantmentHelper#setEnchantments} when modifying the item's enchantments.
     *
     * @param enchantment The enchantment being checked for.
     * @return The level of the enchantment, or 0 if not present.
     */
    default int broomsUnofficial$getEnchantmentLevel(Holder<Enchantment> enchantment) {
        int level = ((ItemStack) this).getItem().broomsUnofficial$getEnchantmentLevel(((ItemStack) this), enchantment);
        return EnchantmentUtil.getEnchantmentLevelSpecific(level, ((ItemStack) this), enchantment);
    }
}
