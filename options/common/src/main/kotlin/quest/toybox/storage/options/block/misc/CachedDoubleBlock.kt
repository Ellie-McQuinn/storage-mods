package quest.toybox.storage.options.block.misc

import net.minecraft.world.CompoundContainer
import quest.toybox.storage.options.block.entity.DoubleInventoryBlockEntity
import quest.toybox.storage.options.platform.CommonHelper

class CachedDoubleBlock<out E : DoubleInventoryBlockEntity<E>>(val first: E, val second: E) {
    val itemAccess by lazy { CommonHelper.INSTANCE.getItemAccess(first, second) }
    val container by lazy { CompoundContainer(first, second) }

    fun <R> calculate(function: (E, E) -> R): R {
        return function.invoke(first, second)
    }
}