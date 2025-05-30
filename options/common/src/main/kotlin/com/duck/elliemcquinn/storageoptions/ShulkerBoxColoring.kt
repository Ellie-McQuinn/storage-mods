package com.duck.elliemcquinn.storageoptions

import com.duck.elliemcquinn.storageoptions.item.ShulkerBoxBlockItem
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import com.duck.elliemcquinn.storageoptions.registration.ModRecipes
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.DyeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level

class ShulkerBoxColoring(category: CraftingBookCategory) : CustomRecipe(category) {
    override fun matches(
        input: CraftingInput,
        level: Level
    ): Boolean {
        val foundDye = input.items().any { it.item is DyeItem }
        val foundShulker = input.items().any { it.item is ShulkerBoxBlockItem }

        return input.ingredientCount() == 2 && foundDye && foundShulker
    }

    override fun assemble(
        input: CraftingInput,
        registries: HolderLookup.Provider
    ): ItemStack {
        val dye = input.items().first { it.item is DyeItem }
        val dyeColor = (dye.item as DyeItem).dyeColor
        val shulker = input.items().first { it.item is ShulkerBoxBlockItem }

        return shulker.transmuteCopy(ModItems.DYED_SHULKER_BOXES[dyeColor]!!, 1)
    }

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = width * height >= 2

    override fun getSerializer(): RecipeSerializer<*> = ModRecipes.SHULKER_BOX_COLORING
}