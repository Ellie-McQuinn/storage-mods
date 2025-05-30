package quest.toybox.storage.library.block.misc

import net.minecraft.util.StringRepresentable

enum class BarrelType : StringRepresentable{
    SINGLE,
    TOP,
    BOTTOM;

    override fun getSerializedName() = when (this) {
        SINGLE -> "single"
        TOP -> "top"
        BOTTOM -> "bottom"
    }
}