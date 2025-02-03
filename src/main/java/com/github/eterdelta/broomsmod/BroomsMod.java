package com.github.eterdelta.broomsmod;

import com.github.eterdelta.broomsmod.registry.BroomsEntities;
import com.github.eterdelta.broomsmod.registry.BroomsItems;
import com.github.eterdelta.broomsmod.registry.BroomsSounds;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;


@Mod(BroomsMod.MODID)
public class BroomsMod {
    public static final String MODID = "broomsmod";

    public BroomsMod(IEventBus eventBus) {
        BroomsEntities.ENTITIES.register(eventBus);
        BroomsItems.ITEMS.register(eventBus);
        BroomsSounds.SOUND_EVENTS.register(eventBus);
        eventBus.addListener(this::addItemsToTabs);
        //NeoForge.EVENT_BUS.register(new TestingChatEvent());
    }


    private void addItemsToTabs(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept((ItemLike) BroomsItems.WOODEN_BROOM);
        }
    }



}
