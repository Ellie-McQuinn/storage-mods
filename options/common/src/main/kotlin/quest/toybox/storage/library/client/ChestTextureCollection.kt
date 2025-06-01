package quest.toybox.storage.library.client

import net.minecraft.client.resources.model.Material
import quest.toybox.storage.library.block.misc.ChestType

data class ChestTextureCollection(
    val single: Material,
    val left: Material,
    val right: Material
) {
    operator fun get(type: ChestType): Material {
        return when(type) {
            ChestType.SINGLE -> single
            ChestType.LEFT -> left
            ChestType.RIGHT -> right
        }
    }
}
