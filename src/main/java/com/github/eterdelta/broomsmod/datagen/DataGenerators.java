package com.github.eterdelta.broomsmod.datagen;

import com.github.eterdelta.broomsmod.BroomsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BroomsMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        generator.addProvider(event.includeServer(), 
            new RegistryDataGenerator(output, lookupProvider));
        //generator.addProvider((event.includeServer()), new BroomsEnchantmentTags(output,lookupProvider, BroomsMod.MODID,exFileHelper));
    }
} 