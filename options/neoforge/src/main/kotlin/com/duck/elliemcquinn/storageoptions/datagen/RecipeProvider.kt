package com.duck.elliemcquinn.storageoptions.datagen

import com.duck.elliemcquinn.storageoptions.EllsSO
import com.duck.elliemcquinn.storageoptions.ShulkerBoxColoring
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.data.recipes.SpecialRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.Tags
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : RecipeProvider(output, registries) {
    override fun buildRecipes(output: RecipeOutput, registries: HolderLookup.Provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BARREL, 2)
            .pattern("PSP")
            .pattern("PBP")
            .pattern("PSP")
            .define('P', ItemTags.PLANKS)
            .define('S', ItemTags.WOODEN_SLABS)
            .define('B', Tags.Items.BARRELS_WOODEN)
            .unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN))
            .save(output)

        SingleItemRecipeBuilder.stonecutting(
            Ingredient.of(Tags.Items.BARRELS_WOODEN),
            RecipeCategory.MISC,
            ModItems.BARREL
        ).unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN)).save(output, "${EllsSO.MOD_ID}:barrel_from_stonecutting")

        SingleItemRecipeBuilder.stonecutting(
            Ingredient.of(Tags.Items.BARRELS_WOODEN),
            RecipeCategory.MISC,
            Items.BARREL
        ).unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN)).save(output, "${EllsSO.MOD_ID}:vanilla_barrel_from_stonecutting")

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CLASSIC_CHEST, 2)
            .pattern("PPP")
            .pattern("PCP")
            .pattern("PPP")
            .define('P', ItemTags.PLANKS)
            .define('C', Tags.Items.CHESTS_WOODEN)
            .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
            .save(output)

        SingleItemRecipeBuilder.stonecutting(
            Ingredient.of(Tags.Items.CHESTS_WOODEN),
            RecipeCategory.MISC,
            ModItems.CLASSIC_CHEST
        ).unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN)).save(output, "${EllsSO.MOD_ID}:classic_chest_from_stonecutting")

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHULKER_BOXES.first())
            .pattern("PSP")
            .pattern("PCP")
            .pattern("PSP")
            .define('P', Items.POPPED_CHORUS_FRUIT)
            .define('S', Items.SHULKER_SHELL)
            .define('C', Tags.Items.CHESTS_WOODEN)
            .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
            .save(output)

        SpecialRecipeBuilder.special(::ShulkerBoxColoring).save(output, EllsSO.id("shulker_box_coloring"))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MINI_CHEST, 8)
            .pattern(" P ")
            .pattern("PCP")
            .pattern(" P ")
            .define('P', Items.PAPER)
            .define('C', Tags.Items.CHESTS_WOODEN)
            .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
            .save(output)

        for (chest in ModItems.CHESTS) {
            SingleItemRecipeBuilder.stonecutting(
                Ingredient.of(Tags.Items.CHESTS_WOODEN),
                RecipeCategory.MISC,
                chest
            ).unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN)).save(output)
        }

        SingleItemRecipeBuilder.stonecutting(
            Ingredient.of(Tags.Items.CHESTS_WOODEN),
            RecipeCategory.MISC,
            Items.CHEST
        ).unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN)).save(output, "${EllsSO.MOD_ID}:vanilla_chest_from_stonecutting")
    }
}