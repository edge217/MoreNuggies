/*
 * Copyright (c) 2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.datagen;

import com.edgeburnmedia.morenuggies.MoreNuggies;
import com.edgeburnmedia.morenuggies.registry.items.MoreNuggiesItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MoreNuggiesItemTagsProvider extends ItemTagsProvider {

    public MoreNuggiesItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), MoreNuggies.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        addNuggetTags();


    }

    private void addNuggetTags() {
        List<Item> items = MoreNuggiesItemRegistry.getItems();
        for (Item item : items) {
            String key = item.toString();
            String mat = key.split("_nugget")[0];

            // Add #more_nuggies:x_smeltable tags
            // Includes #more_nuggies:x_included tag
            tag(ItemTags.create(new ResourceLocation(MoreNuggies.MODID, "%s_smeltable".formatted(mat)))).addOptionalTag(new ResourceLocation(MoreNuggies.MODID, "%s_included".formatted(mat)));

            // Add #forge:nuggets/* tags
            ResourceLocation finishedTag = new ResourceLocation("forge", "nuggets/%s".formatted(mat));
            tag(ItemTags.create(finishedTag)).add(item);
            tag(ItemTags.create(new ResourceLocation("forge", "nuggets"))).addOptionalTag(finishedTag);
        }
    }
}
