package quest.toybox.storage.metallum.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.neoforged.neoforge.common.Tags
import quest.toybox.storage.metallum.registration.ModItems
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : RecipeProvider(output, registries) {
    override fun buildRecipes(output: RecipeOutput, registries: HolderLookup.Provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_BARREL, 2)
            .pattern("III")
            .pattern("IBI")
            .pattern("III")
            .define('I', Tags.Items.INGOTS_COPPER)
            .define('B', Tags.Items.BARRELS_WOODEN)
            .unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN))
            .save(output)
    }
}