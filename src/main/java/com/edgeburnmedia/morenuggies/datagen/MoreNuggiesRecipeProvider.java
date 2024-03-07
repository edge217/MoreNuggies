/*
 * Copyright (c) 2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.datagen;

import com.edgeburnmedia.morenuggies.MoreNuggies;
import com.edgeburnmedia.morenuggies.registry.items.MoreNuggiesItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MoreNuggiesRecipeProvider extends RecipeProvider {
    public MoreNuggiesRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private Map<Item, Item> getResourceItems() {
        Map<Item, Item> map = new HashMap<>();
        map.put(MoreNuggiesItemRegistry.AMETHYST_NUGGET.get(), Items.AMETHYST_SHARD);
        map.put(MoreNuggiesItemRegistry.CHARCOAL_NUGGET.get(), Items.CHARCOAL);
        map.put(MoreNuggiesItemRegistry.COAL_NUGGET.get(), Items.COAL);
        map.put(MoreNuggiesItemRegistry.COBBLESTONE_NUGGET.get(), Items.COBBLESTONE);
        map.put(MoreNuggiesItemRegistry.COOKED_CHICKEN_NUGGET.get(), Items.COOKED_CHICKEN);
        map.put(MoreNuggiesItemRegistry.COPPER_NUGGET.get(), Items.COPPER_INGOT);
        map.put(MoreNuggiesItemRegistry.DIAMOND_NUGGET.get(), Items.DIAMOND);
        map.put(MoreNuggiesItemRegistry.ECHO_NUGGET.get(), Items.ECHO_SHARD);
        map.put(MoreNuggiesItemRegistry.EMERALD_NUGGET.get(), Items.EMERALD);
        map.put(MoreNuggiesItemRegistry.LEATHER_NUGGET.get(), Items.LEATHER);
        map.put(MoreNuggiesItemRegistry.NETHERITE_NUGGET.get(), Items.NETHERITE_INGOT);
        map.put(MoreNuggiesItemRegistry.OBSIDIAN_NUGGET.get(), Items.OBSIDIAN);
        map.put(MoreNuggiesItemRegistry.PHANTOM_MEMBRANE_NUGGET.get(), Items.PHANTOM_MEMBRANE);
        map.put(MoreNuggiesItemRegistry.PRISMARINE_NUGGET.get(), Items.PRISMARINE);
        map.put(MoreNuggiesItemRegistry.QUARTZ_NUGGET.get(), Items.QUARTZ);
        map.put(MoreNuggiesItemRegistry.RAW_CHICKEN_NUGGET.get(), Items.CHICKEN);
        map.put(MoreNuggiesItemRegistry.REDSTONE_NUGGET.get(), Items.REDSTONE);
        map.put(MoreNuggiesItemRegistry.SCUTE_NUGGET.get(), Items.SCUTE);
        map.put(MoreNuggiesItemRegistry.WOODEN_NUGGET.get(), Items.OAK_PLANKS);
        map.put(MoreNuggiesItemRegistry.FLINT_NUGGET.get(), Items.FLINT);
        map.put(MoreNuggiesItemRegistry.LAPIS_LAZULI_NUGGET.get(), Items.LAPIS_LAZULI);
        return map;
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        for (Item item : MoreNuggiesItemRegistry.getItems()) {
            String key = item.toString();
            String mat = key.split("_nugget")[0];
            makeSmeltingRecipe(writer, item, mat, key);

            makeCompactingRecipes(writer, item, getResourceItems().get(item), mat);
        }
    }

    private void makeCompactingRecipes(Consumer<FinishedRecipe> writer, Item nug, Item res, String materialName) {
        // Resource -> Nugs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nug, 9)
                .requires(res)
                .unlockedBy("has_%s".formatted(materialName), has(res))
                .save(writer, new ResourceLocation(MoreNuggies.MODID, "expanding/%s".formatted(materialName)));

        // Nugs -> Resource
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, res)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', nug)
                .unlockedBy("has_nugget", has(ItemTags.create(new ResourceLocation("forge", "nuggets"))))
                .save(writer, new ResourceLocation(MoreNuggies.MODID, "compacting/%s".formatted(materialName)));
    }

    private void makeSmeltingRecipe(@NotNull Consumer<FinishedRecipe> writer, Item item, String mat, String key) {
        String smeltableTagStr = mat + "_smeltable"; // x_smeltable tag
        ResourceLocation smeltableTag = new ResourceLocation(MoreNuggies.MODID, smeltableTagStr);
        SimpleCookingRecipeBuilder r = SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemTags.create(smeltableTag)), RecipeCategory.MISC, item, 0.5f, 100).unlockedBy("has_%s".formatted(key), has(item));
        r.save(writer, new ResourceLocation(MoreNuggies.MODID, "smelting/%s".formatted(key)));
    }
}
