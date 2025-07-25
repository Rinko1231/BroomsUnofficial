package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.item.SuccubaHatItem;
import com.github.eterdelta.broomsmod.item.WoodenBroomItem;
import com.github.eterdelta.broomsmod.util.RegistryHelper;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BroomsItems {
    public static final Item WOODEN_BROOM = RegistryHelper.registerItem(
            "wooden_broom", new WoodenBroomItem(new Item.Properties().stacksTo(1))
    );

    public static final Item SUCCUBA_HAT = RegistryHelper.registerItem(
            "succuba_hat_item", new SuccubaHatItem(ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(16)).rarity(Rarity.EPIC))
    );

    public static void init() {
        BroomsMod.LOGGER.info("BroomsMod Item registered");
    }
}
