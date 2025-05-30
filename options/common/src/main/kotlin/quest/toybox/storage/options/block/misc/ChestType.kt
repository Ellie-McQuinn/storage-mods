package quest.toybox.storage.options.block.misc

import net.minecraft.util.StringRepresentable

enum class ChestType : StringRepresentable {
    SINGLE,
    LEFT,
    RIGHT;

    override fun getSerializedName() = when (this) {
        SINGLE -> "single"
        LEFT -> "left"
        RIGHT -> "right"
    }
}