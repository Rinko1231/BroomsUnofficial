package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.item.WoodenBroomItem;
import com.github.eterdelta.broomsmod.util.RegistryHelper;
import net.minecraft.world.item.Item;

public class BroomsItems {
    public static final Item WOODEN_BROOM = RegistryHelper.registerItem(
            "wooden_broom", new WoodenBroomItem(new Item.Properties().stacksTo(1))
    );

    public static void init() {
        BroomsMod.LOGGER.info("BroomsMod Item registered");
    }
}
