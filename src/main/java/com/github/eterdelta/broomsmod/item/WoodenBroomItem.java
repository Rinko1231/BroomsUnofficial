package com.github.eterdelta.broomsmod.item;

import com.github.eterdelta.broomsmod.entity.WoodenBroomEntity;
import com.github.eterdelta.broomsmod.registry.BroomsEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;

import static com.github.eterdelta.broomsmod.registry.BroomsEnchantments.getHolder;

public class WoodenBroomItem extends Item {

    public WoodenBroomItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        } else {
            if (hitResult.getType() == HitResult.Type.BLOCK) {


                Holder<Enchantment> holderAirSkills = getHolder(level, BroomsEnchantments.AIR_SKILLS);
                Holder<Enchantment> holderLandSkill = getHolder(level, BroomsEnchantments.LAND_SKILLS);
                Holder<Enchantment> holderHovering = getHolder(level, BroomsEnchantments.HOVERING);
                Holder<Enchantment> holderSeaBreeze = getHolder(level, BroomsEnchantments.SEA_BREEZE);

                int hoveringLevel = player.getItemInHand(InteractionHand.MAIN_HAND).broomsUnofficial$getEnchantmentLevel(holderHovering);

                int airSkillLevel = EnchantmentHelper.getItemEnchantmentLevel(holderAirSkills, itemStack);
                int landSkillLevel = EnchantmentHelper.getItemEnchantmentLevel(holderLandSkill, itemStack);
                //int hoveringLevel = EnchantmentHelper.getTagEnchantmentLevel(holderHovering, itemStack);
                int seaBreezeLevel = EnchantmentHelper.getItemEnchantmentLevel(holderSeaBreeze, itemStack);

                WoodenBroomEntity broom = new WoodenBroomEntity(itemStack, level, hitResult.getLocation().x(), hitResult.getLocation().y(), hitResult.getLocation().z(), airSkillLevel, landSkillLevel, hoveringLevel, seaBreezeLevel);
                broom.setYRot(player.getYRot());

                if (!level.noCollision(broom, broom.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemStack);
                } else {
                    if (!level.isClientSide()) {
                        level.addFreshEntity(broom);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        if (!player.getAbilities().instabuild) {
                            itemStack.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemStack);
            }
        }
    }
}
