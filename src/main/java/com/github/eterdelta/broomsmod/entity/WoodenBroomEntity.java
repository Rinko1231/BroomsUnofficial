package com.github.eterdelta.broomsmod.entity;

import com.github.eterdelta.broomsmod.registry.BroomsEntities;
import com.github.eterdelta.broomsmod.registry.BroomsItems;
import com.github.eterdelta.broomsmod.registry.BroomsSounds;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class WoodenBroomEntity extends Entity {
    private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> HURT_TIME = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HURT_DIR = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> A_S = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> L_S = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> H_V = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> S_B = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(WoodenBroomEntity.class, EntityDataSerializers.FLOAT);
    public int hoverTime;
    public boolean canHover;
    public boolean seaBreezing;
    protected boolean inputLeft;
    protected boolean inputRight;
    protected boolean inputUp;
    protected boolean inputDown;
    protected boolean inputJump;
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;

/*
    private int seaBreezeLevel;

    public int getSeaBreezeLevel()
    {
        return this.seaBreezeLevel;
    }*/

   public WoodenBroomEntity(EntityType<WoodenBroomEntity> entityType, Level level) {
       super(entityType, level);
       this.blocksBuilding = true;
       this.canHover = true;
       //this.hoverTime = this.getMaxHoverTime();
   }

    // 辅助构造函数
    public WoodenBroomEntity(ItemStack itemStack, Level level, double x, double y, double z, int as, int ls, int hv, int sb) {
        this(BroomsEntities.WOODEN_BROOM.get(), level);
        this.entityData.set(ITEM, itemStack.copy());
        this.entityData.set(A_S, as);
        this.entityData.set(L_S, ls);
        this.entityData.set(H_V, hv);
        this.entityData.set(S_B, sb);
        this.hoverTime = this.getMaxHoverTime();
        //this.seaBreezeLevel = sb;
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }


    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ITEM, ItemStack.EMPTY);
        builder.define(HURT_TIME, 0);
        builder.define(HURT_DIR, 1);
        builder.define(DAMAGE, 0.0F);
        builder.define(A_S, 0);
        builder.define(L_S, 0);
        builder.define(H_V, 0);
        builder.define(S_B, 0);
    }


    @Override
    public boolean isPushable() {
        return true;
    }


    public double getPassengersRidingOffset() {
        return 0.1D;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.level().isClientSide && !this.isRemoved()) {
            this.setHurtDir(-this.getHurtDir());
            this.setHurtTime(10);
            this.setDamage(this.getDamage() + amount * 10.0F);
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
            boolean flag = source.getEntity() instanceof Player && ((Player) source.getEntity()).getAbilities().instabuild;
            if (flag || this.getDamage() > 40.0F) {
                if (!flag && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    this.spawnBroomItem();
                }
                this.discard();
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void push(@NotNull Entity entity) {
        if (entity instanceof WoodenBroomEntity) {
            if (entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.push(entity);
            }
        } else if (entity.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.push(entity);
        }
    }

    @Override
    public void animateHurt(float p_265161_) {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    public void lerpTo(double p_38299_, double p_38300_, double p_38301_, float p_38302_, float p_38303_, int p_38304_, boolean p_38305_) {
        this.lerpX = p_38299_;
        this.lerpY = p_38300_;
        this.lerpZ = p_38301_;
        this.lerpYRot = p_38302_;
        this.lerpXRot = p_38303_;
        this.lerpSteps = p_38304_;
    }

    @Override
    public @NotNull Direction getMotionDirection() {
        return this.getDirection().getClockWise();
    }



    @Override
    public void tick() {
        super.tick();
        this.tickLerp();

        this.setDeltaMovement(this.getDeltaMovement().multiply(0.8D, 0.9D, 0.8D));

        if (!this.onGround() && !this.seaBreezing) {
            if (this.hoverTime > 0) {
                this.canHover = true;
                this.hoverTime--;

                if (this.hoverTime == 5) {
                    this.playSound(BroomsSounds.BROOM_FALL.value(), 1.0F, 1.0F);
                }
            } else {
                this.canHover = false;
            }
        } else if (this.hoverTime != this.getMaxHoverTime()) {
            this.canHover = true;
            this.hoverTime = this.getMaxHoverTime();
        }

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
        }

        if (this.getHurtTime() > 0) {
            this.setHurtTime(this.getHurtTime() - 1);
        }

        if (this.getDamage() > 0.0F) {
            this.setDamage(this.getDamage() - 1.0F);
        }

        if (this.isVehicle()) {
            this.setYRot(Objects.requireNonNull(this.getControllingPassenger()).getYRot());
        }

        if (this.isControlledByLocalInstance()) {
            if (this.level().isClientSide) {
                this.handleInputs();
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        if (this.level().getBlockState(this.blockPosition().below()).is(Blocks.WATER) && this.getS_B() >0) {
            this.seaBreezing = true;
            if (this.getDeltaMovement().y() < 0) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.9D, 0.3D, 0.9D));
            }
        } else {
            this.seaBreezing = false;
        }

        this.checkInsideBlocks();
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for (Entity entity : list) {
                if (!entity.hasPassenger(this)) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                        entity.startRiding(this);
                    } else {
                        this.push(entity);
                    }
                }
            }
        }

        if (this.isInWaterOrBubble()) {
            this.playSound(BroomsSounds.BROOM_DESTROY.value(), 0.8F, 1.0F);
            this.spawnBroomItem();
            this.discard();
        }
    }

    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
        }

        if (this.lerpSteps > 0) {
            double stepX = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
            double stepY = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
            double stepZ = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
            float stepYRot = (float) (this.getYRot() + Mth.wrapDegrees(this.lerpYRot - this.getYRot()) / this.lerpSteps);
            float stepXRot = this.getXRot() + (float) (this.lerpXRot - (double) this.getXRot()) / (float) this.lerpSteps;

            this.setYRot(stepYRot);
            this.setXRot(stepXRot);
            --this.lerpSteps;
            this.setPos(stepX, stepY, stepZ);
            this.setRot(this.getYRot(), this.getXRot());
        }
    }

    private void handleInputs() {
        if (this.isVehicle()) {
            LivingEntity controller = this.getControllingPassenger();
            Vec3 inputVector = this.getInputVector(new Vec3(controller.xxa * 0.8F, 0.0D, controller.zza), this.getSpeed(), this.getYRot());

            if (this.inputLeft || this.inputRight || this.inputUp || this.inputDown) {
                this.setDeltaMovement(this.getDeltaMovement().add(inputVector));
            }

            if (this.inputJump && this.canHover) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, this.getHoverSpeed(), 0.0D));
            }
        }
    }
    

    @Override
    public void positionRider(@NotNull Entity rider, @NotNull MoveFunction p_19958_) {
        if (this.hasPassenger(rider)) {
            double d0 = this.getY() + this.getPassengersRidingOffset() + this.getPassengersRidingOffset();
            p_19958_.accept(rider, this.getX(), d0, this.getZ());
        }
        if (rider instanceof Player player) {
            player.setYBodyRot(player.getYHeadRot());
        }
    }


    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        //compoundTag.put("Item", this.getItem().save(new CompoundTag()));
        if (this.level() == null) return;
        HolderLookup.Provider provider = this.level().registryAccess();
        // 创建新Tag
        CompoundTag itemTag = new CompoundTag();
        this.getItem().save(provider, itemTag);
        // 存入实体NBT
        compoundTag.put("Item", itemTag);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        //CompoundTag itemTag = compoundTag.getCompound("Item");
        //this.setItem(ItemStack.of(itemTag));
        if (this.level() == null) return;
        // 检查是否存在有效的Item Tag
        if (compoundTag.contains("Item", Tag.TAG_COMPOUND)) {
            HolderLookup.Provider provider = this.level().registryAccess();
            CompoundTag itemTag = compoundTag.getCompound("Item");
            ItemStack stack = ItemStack.parseOptional(provider, itemTag);
            this.setItem(stack);
        }
    }

    @Override
    public @NotNull InteractionResult interact(Player p_38330_, @NotNull InteractionHand p_38331_) {
        if (p_38330_.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else {
            if (!this.level().isClientSide()) {
                return p_38330_.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            } else {
                return InteractionResult.SUCCESS;
            }
        }
    }

    @Override
    protected void checkFallDamage(double p_19911_, boolean p_19912_, @NotNull BlockState p_19913_, @NotNull BlockPos p_19914_) {
        this.fallDistance = 0.0F;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }

    public void setInputs(boolean left, boolean right, boolean up, boolean down, boolean jumping) {
        this.inputLeft = left;
        this.inputRight = right;
        this.inputUp = up;
        this.inputDown = down;
        this.inputJump = jumping;
    }

    /*@Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }*/
    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket(@NotNull ServerEntity serverEntity) {
        // 使用原版 ClientboundAddEntityPacket
        return new ClientboundAddEntityPacket(this, serverEntity, 0);
    }


    @Override
    protected void addPassenger(@NotNull Entity passenger) {
        super.addPassenger(passenger);
        if (this.isControlledByLocalInstance() && this.lerpSteps > 0) {
            this.lerpSteps = 0;
            this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float) this.lerpYRot, (float) this.lerpXRot);
        }
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(BroomsItems.WOODEN_BROOM.get());
    }

    public Vec3 getInputVector(Vec3 movement, float speed, float angle) {
        double length = movement.lengthSqr();
        if (length < 1.0E-7D) {
            return Vec3.ZERO;
        } else {
            Vec3 vec3 = (length > 1.0D ? movement.normalize() : movement).scale(speed);
            float f = Mth.sin(angle * ((float) Math.PI / 180F));
            float f1 = Mth.cos(angle * ((float) Math.PI / 180F));
            return new Vec3(vec3.x * (double) f1 - vec3.z * (double) f, vec3.y, vec3.z * (double) f1 + vec3.x * (double) f);
        }
    }

    public void spawnBroomItem() {
        if (!this.getItem().isEmpty()) {
            this.spawnAtLocation(this.getItem());
        } else {
            this.spawnAtLocation(new ItemStack(BroomsItems.WOODEN_BROOM.get()));
        }
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setDamage(float p_38312_) {
        this.entityData.set(DAMAGE, p_38312_);
    }

    public int getHurtTime() {
        return this.entityData.get(HURT_TIME);
    }

    public void setHurtTime(int p_38355_) {
        this.entityData.set(HURT_TIME, p_38355_);
    }

    public int getHurtDir() {
        return this.entityData.get(HURT_DIR);
    }

    public void setHurtDir(int p_38363_) {
        this.entityData.set(HURT_DIR, p_38363_);
    }

    public ItemStack getItem() {
        return this.entityData.get(ITEM);
    }

    public int getA_S() {
        return this.entityData.get(A_S);
    }
    public int getL_S() {
        return this.entityData.get(L_S);
    }
    public int getH_V() {
        return this.entityData.get(H_V);
    }
    public int getS_B() {
        return this.entityData.get(S_B);
    }





    public void setItem(ItemStack itemStack) {
        this.entityData.set(ITEM, itemStack);
    }

    public double getHoverSpeed() {
        return 0.09D;
    }

    public float getSpeed() {
        if (this.onGround()) {
            return 0.08F + (0.08F * (this.getL_S() * 20 / 100.0F));
        } else {
            return 0.08F + (0.08F * (this.getA_S() * 20 / 100.0F));
        }
    }

    public int getMaxHoverTime() {
        return (int) (100 + (100 * (this.getH_V() * 25 / 100.0F)));
    }
}
