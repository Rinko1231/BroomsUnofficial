package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.item.WoodenBroomItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class BroomsItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(BroomsMod.MODID);

    public static final DeferredHolder<Item, WoodenBroomItem> WOODEN_BROOM = ITEMS.register("wooden_broom", () -> new WoodenBroomItem(new Item.Properties().stacksTo(1)));






}
