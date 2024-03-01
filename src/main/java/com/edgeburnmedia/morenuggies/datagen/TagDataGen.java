/*
 * Copyright (c) 2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.datagen;

import com.edgeburnmedia.morenuggies.MoreNuggies;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MoreNuggies.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TagDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        MoreNuggiesBlockTagsProvider blockTagsProvider = new MoreNuggiesBlockTagsProvider(packOutput, lookupProvider, MoreNuggies.MODID, event.getExistingFileHelper());
        event.getGenerator().addProvider(event.includeServer(), blockTagsProvider);
        event.getGenerator().addProvider(event.includeServer(), new MoreNuggiesItemTagsProvider(packOutput, lookupProvider, blockTagsProvider, event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), new MoreNuggiesRecipeProvider(packOutput));
    }
}
