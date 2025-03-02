package com.github.eterdelta.broomsmod;

import com.github.eterdelta.broomsmod.registry.BroomsEntities;
import com.github.eterdelta.broomsmod.registry.BroomsGamerule;
import com.github.eterdelta.broomsmod.registry.BroomsItems;
import com.github.eterdelta.broomsmod.registry.BroomsSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BroomsMod implements ModInitializer {
    public static final String MODID = "broomsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        BroomsEntities.init();
        BroomsItems.init();
        BroomsSounds.init();
        BroomsGamerule.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(BroomsItems.WOODEN_BROOM);
            entries.accept(BroomsItems.SUCCUBA_HAT);
        });
    }
}
