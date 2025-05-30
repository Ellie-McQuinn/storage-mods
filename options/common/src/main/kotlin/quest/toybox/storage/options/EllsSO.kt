package quest.toybox.storage.options

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object EllsSO {
    const val MOD_ID = "storageoptions"

    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, path)

    // todo: remove in 1.22
    fun oldId(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath("ellsso", path)

    @Suppress("DEPRECATION")
    fun id(block: Block): ResourceLocation = block.builtInRegistryHolder().key().location()

    @Suppress("DEPRECATION")
    fun id(item: Item): ResourceLocation = item.builtInRegistryHolder().key().location()
}