package com.github.eterdelta.broomsmod.datagen;

import com.github.eterdelta.broomsmod.registry.BroomsEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends FabricDynamicRegistryProvider {
    public RegistryDataGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        BroomsEnchantments.bootstrap(entries);
    }

    @Override
    public @NotNull String getName() {
        return "BroomsModDynamicRegistryDataGenerator";
    }
}
