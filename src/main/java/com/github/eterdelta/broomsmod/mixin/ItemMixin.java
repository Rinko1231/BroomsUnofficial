package com.github.eterdelta.broomsmod.mixin;

import com.github.eterdelta.broomsmod.extension.ItemExtension;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin implements ItemExtension {
    @Override
    public int broomsUnofficial$getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        return stack.getEnchantments().getLevel(enchantment);
    }
}
