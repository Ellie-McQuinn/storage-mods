package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.DoubleInventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.InventoryBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import java.util.ServiceLoader

interface CommonHelper {
    companion object {
        val INSTANCE: CommonHelper = ServiceLoader.load(CommonHelper::class.java).first()
    }

    fun getItemAccess(entity: InventoryBlockEntity): Any
    fun getItemAccess(first: DoubleInventoryBlockEntity<*>, second: DoubleInventoryBlockEntity<*>): Any

    fun invalidateCapabilities(level: Level, pos: BlockPos)
}