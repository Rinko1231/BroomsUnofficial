package com.github.eterdelta.broomsmod.registry;

import com.github.eterdelta.broomsmod.BroomsMod;
import com.github.eterdelta.broomsmod.item.SuccubaHatItem;
import com.github.eterdelta.broomsmod.item.WoodenBroomItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class BroomsItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(BroomsMod.MODID);

    public static final DeferredHolder<Item, WoodenBroomItem> WOODEN_BROOM = ITEMS.register("wooden_broom", () -> new WoodenBroomItem(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, SuccubaHatItem> SUCCUBA_HAT = ITEMS.register(
            "succuba_hat_item", () ->  new SuccubaHatItem(ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(16)).rarity(Rarity.EPIC))
    );

    public static final DeferredHolder<Item, SuccubaHatItem> SUCCUBA_HAT_FAKE = ITEMS.register(
            "succuba_hat_fake_item", () ->  new SuccubaHatItem(ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(16)).rarity(Rarity.EPIC))
    );




}
