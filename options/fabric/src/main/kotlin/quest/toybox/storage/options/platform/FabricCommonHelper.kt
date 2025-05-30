package quest.toybox.storage.options.platform

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import quest.toybox.storage.options.block.entity.DoubleInventoryBlockEntity
import quest.toybox.storage.options.block.entity.InventoryBlockEntity

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