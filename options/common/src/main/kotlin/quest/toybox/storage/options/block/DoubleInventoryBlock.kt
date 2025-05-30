package quest.toybox.storage.options.block

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.block.entity.DoubleInventoryBlockEntity

abstract class DoubleInventoryBlock(properties: Properties) : InventoryBlock(properties) {
    abstract fun getDoubleContainerName(): Component

    override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider? {
        val entity = level.getBlockEntity(pos) as? DoubleInventoryBlockEntity<*> ?: return null
        val doubleCache = entity.getDoubleCache()

        if (doubleCache == null) {
            return super.getMenuProvider(state, level, pos)
        }

        val first = doubleCache.first
        val second = doubleCache.second
        val inventory = doubleCache.container

        if (isAccessBlocked(level, first.blockState, first.blockPos) || isAccessBlocked(level, second.blockState, second.blockPos)) {
            return null
        }

        return SimpleMenuProvider(fun (id: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu? {
            return if (first.canOpen(player) && second.canOpen(player)) {
                first.unpackLootTable(player)
                second.unpackLootTable(player)

                ChestMenu.sixRows(id, playerInventory, inventory)
            } else {
                null
            }
        }, first.customName ?: second.customName ?: this.getDoubleContainerName())
    }

    override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos): Int {
        val entity = level.getBlockEntity(pos) as? DoubleInventoryBlockEntity<*> ?: return 0

        val doubleCache = entity.getDoubleCache()

        return if (doubleCache == null) {
            AbstractContainerMenu.getRedstoneSignalFromContainer(entity)
        } else {
            AbstractContainerMenu.getRedstoneSignalFromContainer(doubleCache.container)
        }
    }
}