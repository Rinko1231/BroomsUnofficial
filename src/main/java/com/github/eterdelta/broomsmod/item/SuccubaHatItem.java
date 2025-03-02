package com.github.eterdelta.broomsmod.item;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SuccubaHatItem extends Item implements Equipable {

    protected final ArmorItem.Type type;

    public SuccubaHatItem(ArmorItem.Type type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return this.swapWithEquipmentSlot(this, level, player, interactionHand);
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }

    public static enum Type implements StringRepresentable {
        HELMET(EquipmentSlot.HEAD, 11, "helmet");

        public static final Codec<ArmorItem.Type> CODEC = StringRepresentable.fromValues(ArmorItem.Type::values);
        private final EquipmentSlot slot;
        private final String name;
        private final int durability;

        private Type(final EquipmentSlot equipmentSlot, final int j, final String string2) {
            this.slot = equipmentSlot;
            this.name = string2;
            this.durability = j;
        }

        public int getDurability(int i) {
            return this.durability * i;
        }

        public EquipmentSlot getSlot() {
            return this.slot;
        }

        public String getName() {
            return this.name;
        }

        public boolean hasTrims() {
            return false;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
