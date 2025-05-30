package quest.toybox.storage.options

import net.minecraft.resources.ResourceLocation

object EllsSO {
    const val MOD_ID = "storageoptions"

    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
}