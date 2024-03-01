/*
 * Copyright (c) 2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.registry.items;

import com.edgeburnmedia.morenuggies.MoreNuggies;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MoreNuggiesItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreNuggies.MODID);

    public static final RegistryObject<Item> AMETHYST_NUGGET = ITEMS.register("amethyst_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COOKED_CHICKEN_NUGGET = ITEMS.register(
            "cooked_chicken_nugget", () -> new Item(new Item.Properties().food(ChickenNuggiesFoodProperties.COOKED_CHICKEN_NUGGET)));

    public static final RegistryObject<Item> COAL_NUGGET = ITEMS.register("coal_nugget",
            () -> new MiniFuelItem(new Item.Properties()));

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
            () -> new MiniFuelItem(new Item.Properties()));

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreNuggies.MODID);

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (Field field : MoreNuggiesItemRegistry.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers()) || field.getName().equals("CREATIVE_TAB")) {
                continue;
            }

            field.setAccessible(true);
            try {
                Object o = field.get(null);
                if (o instanceof RegistryObject<?> ro) {
                    items.add((Item) ro.get());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        if (items.isEmpty()) {
            throw new RuntimeException("Items list is empty. Reflection issue?");
        }
        return items;
    }

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("more_nuggies_creative_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> COOKED_CHICKEN_NUGGET.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.more_nuggies"))
            .displayItems((parameters, output) -> {
                for (Item item : getItems()) {
                    output.accept(item.getDefaultInstance());
                }
            }).build());

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public static class MiniFuelItem extends Item {

        public MiniFuelItem(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 200; // enough to burn 1 item
        }
    }

}
