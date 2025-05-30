package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.DoubleInventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.InventoryBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.InvWrapper

class NeoForgeCommonHelper : CommonHelper {
    override fun getItemAccess(entity: InventoryBlockEntity): Any {
        return InvWrapper(entity)
    }

    override fun getItemAccess(
        first: DoubleInventoryBlockEntity<*>,
        second: DoubleInventoryBlockEntity<*>
    ): Any {
        return CombinedInvWrapper(first.getOwnAccess() as InvWrapper, second.getOwnAccess() as InvWrapper)
    }

    override fun invalidateCapabilities(level: Level, pos: BlockPos) {
        level.invalidateCapabilities(pos)
    }
}