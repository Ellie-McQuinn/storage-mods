package quest.toybox.storage.library.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.library.block.menu.MiniChestMenu

class MiniChestBlockEntity(type: BlockEntityType<MiniChestBlockEntity>, pos: BlockPos, state: BlockState) :
    InventoryBlockEntity(type, pos, state) {
    override fun getDefaultName(): Component = Component.translatable("container.storageoptions.mini_chest")

    override fun getContainerSize(): Int = 1

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player) =
        MiniChestMenu(containerId, playerInventory, this)
}