/*
 * Copyright (c) 2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.registry.items;

import com.edgeburnmedia.morenuggies.MoreNuggies;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MoreNuggiesItemRegistry {
    private static final Item CREATIVE_TAB_ITEM = Items.DIRT; // FIXME: make this an item from the mod

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreNuggies.MODID);

    public static final RegistryObject<Item> AMETHYST_NUGGET = ITEMS.register("amethyst_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COOKED_CHICKEN_NUGGET = ITEMS.register(
            "cooked_chicken_nugget", () -> new Item(new Item.Properties().food(ChickenNuggiesFoodProperties.COOKED_CHICKEN_NUGGET)));

    public static final RegistryObject<Item> COAL_NUGGET = ITEMS.register("coal_nugget",
            () -> new Item(new Item.Properties()) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 200;
                }
            });

    public static final RegistryObject<Item> COBBLESTONE_NUGGET = ITEMS.register(
            "cobblestone_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_NUGGET = ITEMS.register("diamond_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ECHO_NUGGET = ITEMS.register("echo_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_NUGGET = ITEMS.register("emerald_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEATHER_NUGGET = ITEMS.register("leather_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_NUGGET = ITEMS.register("netherite_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIAN_NUGGET = ITEMS.register("obsidian_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PHANTOM_MEMBRANE_NUGGET = ITEMS.register(
            "phantom_membrane_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PRISMARINE_NUGGET = ITEMS.register("prismarine_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUARTZ_NUGGET = ITEMS.register("quartz_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_CHICKEN_NUGGET = ITEMS.register(
            "raw_chicken_nugget", () -> new Item(new Item.Properties().food(ChickenNuggiesFoodProperties.RAW_CHICKEN_NUGGET)));

    public static final RegistryObject<Item> REDSTONE_NUGGET = ITEMS.register("redstone_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCUTE_NUGGET = ITEMS.register("scute_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOODEN_NUGGET = ITEMS.register("wooden_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHARCOAL_NUGGET = ITEMS.register("charcoal_nugget",
            () -> new Item(new Item.Properties()) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 200;
                }
            });

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreNuggies.MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("more_nuggies_creative_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(CREATIVE_TAB_ITEM::getDefaultInstance)
            .displayItems((parameters, output) -> {
                for (Field field : MoreNuggiesItemRegistry.class.getFields()) {
                    if (!Modifier.isStatic(field.getModifiers()) || field.getName().equals("CREATIVE_TAB")) {
                        continue;
                    }
                    field.setAccessible(true);
                    try {
                        Object o = field.get(null);
                        if (o instanceof RegistryObject<?> ro) {
                            output.accept(((Item) ro.get()).getDefaultInstance());
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).build());

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

}
