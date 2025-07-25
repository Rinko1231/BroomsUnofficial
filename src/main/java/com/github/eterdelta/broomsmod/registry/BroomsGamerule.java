package com.github.eterdelta.broomsmod.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class BroomsGamerule {
    public static final GameRules.Key<GameRules.BooleanValue> ELAINA = GameRuleRegistry.register(
            "turnToElaina", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false)
    );

    public static void init() {

    }
}
