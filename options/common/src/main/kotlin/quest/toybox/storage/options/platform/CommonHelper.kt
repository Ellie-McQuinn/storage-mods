package quest.toybox.storage.options.platform

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import quest.toybox.storage.library.block.entity.DoubleInventoryBlockEntity
import quest.toybox.storage.library.block.entity.InventoryBlockEntity
import java.util.ServiceLoader

interface CommonHelper {
    companion object {
        val INSTANCE: CommonHelper = ServiceLoader.load(CommonHelper::class.java).first()
    }

    fun getItemAccess(entity: InventoryBlockEntity): Any
    fun getItemAccess(first: DoubleInventoryBlockEntity<*>, second: DoubleInventoryBlockEntity<*>): Any

    fun invalidateCapabilities(level: Level, pos: BlockPos)
}