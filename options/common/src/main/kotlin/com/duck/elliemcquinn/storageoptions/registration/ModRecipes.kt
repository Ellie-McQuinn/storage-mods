package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.EllsSO
import com.duck.elliemcquinn.storageoptions.ShulkerBoxColoring
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer

object ModRecipes {
    val SHULKER_BOX_COLORING: SimpleCraftingRecipeSerializer<ShulkerBoxColoring> = Registry.register(
        BuiltInRegistries.RECIPE_SERIALIZER,
        EllsSO.id("shulker_box_coloring"),
        SimpleCraftingRecipeSerializer(::ShulkerBoxColoring)
    )

    fun init() {
        // NO-OP for now.
    }
}