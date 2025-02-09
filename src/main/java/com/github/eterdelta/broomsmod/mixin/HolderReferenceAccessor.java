package com.github.eterdelta.broomsmod.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Holder.Reference.class)
public interface HolderReferenceAccessor<T> extends Holder<T> {

    @Accessor("owner")
    HolderOwner<T> getOwner();
}
