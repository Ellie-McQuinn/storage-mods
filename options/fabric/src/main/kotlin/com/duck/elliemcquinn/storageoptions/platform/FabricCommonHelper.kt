package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.DoubleInventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.InventoryBlockEntity
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class FabricCommonHelper : CommonHelper {
    override fun getItemAccess(entity: InventoryBlockEntity): Any {
        return InventoryStorage.of(entity, null)
    }

    override fun getItemAccess(
        first: DoubleInventoryBlockEntity<*>,
        second: DoubleInventoryBlockEntity<*>
    ): Any {
        return CombinedStorage(listOf(first.getOwnAccess() as InventoryStorage, second.getOwnAccess() as InventoryStorage))
    }

    override fun invalidateCapabilities(level: Level, pos: BlockPos) {
        // NO-OP neoforge specific.
    }
}