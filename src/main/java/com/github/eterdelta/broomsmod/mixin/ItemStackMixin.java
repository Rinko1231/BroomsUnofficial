package com.github.eterdelta.broomsmod.mixin;

import com.github.eterdelta.broomsmod.extension.ItemStackExtension;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackExtension {
    @Shadow public abstract Item getItem();

    @Override
    public int broomsUnofficial$getEnchantmentLevel(Holder<Enchantment> enchantment) {
        return getItem().broomsUnofficial$getEnchantmentLevel((ItemStack) (Object) this, enchantment);
    }
}
