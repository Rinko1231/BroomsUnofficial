package com.github.eterdelta.broomsmod.extension;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.ApiStatus;

public interface ItemExtension {
    /**
     * Gets the level of the enchantment currently present on the stack. By default, returns the enchantment level present in NBT.
     * Most enchantment implementations rely upon this method.
     *
     * @param stack       The item stack being checked
     * @param enchantment The enchantment being checked for
     * @return Level of the enchantment, or 0 if not present
     * @apiNote Call via {@link ItemStackExtension#broomsUnofficial$getEnchantmentLevel}.
     */
    @ApiStatus.OverrideOnly
    default int broomsUnofficial$getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        ItemEnchantments itemenchantments = stack.getEnchantments();
        return itemenchantments.getLevel(enchantment);
    }
}
