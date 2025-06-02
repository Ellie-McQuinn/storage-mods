package quest.toybox.storage.metallum

import net.minecraft.resources.ResourceLocation

object EllsSM {
    const val MOD_ID: String = "storagemetallum"

    fun id(path: String) = ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
}