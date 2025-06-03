package quest.toybox.storage.metallum.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.neoforged.neoforge.common.Tags
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.registration.ModItems
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : RecipeProvider(output, registries) {
    override fun buildRecipes(output: RecipeOutput, registries: HolderLookup.Provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_BARREL, 1)
            .pattern("III")
            .pattern("IBI")
            .pattern("III")
            .define('I', Tags.Items.INGOTS_COPPER)
            .define('B', Tags.Items.BARRELS_WOODEN)
            .unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN))
            .save(output)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BARREL, 1)
            .pattern("NNN")
            .pattern("IBI")
            .pattern("NNN")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('N', Tags.Items.NUGGETS_IRON)
            .define('B', ModItems.COPPER_BARREL)
            .unlockedBy("has_barrel", has(ModItems.COPPER_BARREL))
            .save(output, "${EllsSM.MOD_ID}:iron_barrel_cheap")

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BARREL, 1)
            .pattern("III")
            .pattern("IBI")
            .pattern("III")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('B', Tags.Items.BARRELS_WOODEN)
            .unlockedBy("has_barrel", has(Tags.Items.BARRELS_WOODEN))
            .save(output, "${EllsSM.MOD_ID}:iron_barrel_expensive")

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_BARREL, 1)
            .pattern("III")
            .pattern("IBI")
            .pattern("III")
            .define('I', Tags.Items.INGOTS_GOLD)
            .define('B', ModItems.IRON_BARREL)
            .unlockedBy("has_barrel", has(ModItems.IRON_BARREL))
            .save(output)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_BARREL, 1)
            .pattern("GDG")
            .pattern("DBD")
            .pattern("GDG")
            .define('G', Tags.Items.GLASS_BLOCKS_CHEAP)
            .define('D', Tags.Items.GEMS_DIAMOND)
            .define('B', ModItems.GOLDEN_BARREL)
            .unlockedBy("has_barrel", has(ModItems.GOLDEN_BARREL))
            .save(output)

        netheriteSmithing(output, ModItems.DIAMOND_BARREL, RecipeCategory.MISC, ModItems.NETHERITE_BARREL)
    }
}