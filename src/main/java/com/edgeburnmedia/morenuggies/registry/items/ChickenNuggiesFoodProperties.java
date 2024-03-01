/*
 * Copyright (c) 2023-2024 Edgeburn Media. All rights reserved.
 */

package com.edgeburnmedia.morenuggies.registry.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public final class ChickenNuggiesFoodProperties {

    public static final FoodProperties RAW_CHICKEN_NUGGET = new FoodProperties.Builder()
            .fast()
            .meat()
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .nutrition(1)
            .saturationMod(0.2f)
            .build();
    public static final FoodProperties COOKED_CHICKEN_NUGGET = new FoodProperties.Builder()
            .fast()
            .meat()
            .nutrition(3)
            .saturationMod(0.3f)
            .build();

    private ChickenNuggiesFoodProperties() {
        throw new IllegalStateException("Utility class");
    }
}
