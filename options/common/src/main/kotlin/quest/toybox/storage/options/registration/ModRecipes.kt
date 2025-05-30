package quest.toybox.storage.options.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.options.ShulkerBoxColoring

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