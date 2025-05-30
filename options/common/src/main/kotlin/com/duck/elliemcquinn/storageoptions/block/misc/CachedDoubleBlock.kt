package com.duck.elliemcquinn.storageoptions.block.misc

import com.duck.elliemcquinn.storageoptions.block.entity.DoubleInventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.platform.CommonHelper
import net.minecraft.world.CompoundContainer

class CachedDoubleBlock<out E : DoubleInventoryBlockEntity<E>>(val first: E, val second: E) {
    val itemAccess by lazy { CommonHelper.INSTANCE.getItemAccess(first, second) }
    val container by lazy { CompoundContainer(first, second) }

    fun <R> calculate(function: (E, E) -> R): R {
        return function.invoke(first, second)
    }
}