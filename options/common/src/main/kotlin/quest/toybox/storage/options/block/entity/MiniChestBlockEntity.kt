package quest.toybox.storage.options.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.block.menu.MiniChestMenu
import quest.toybox.storage.options.registration.ModBlockEntities

class MiniChestBlockEntity(pos: BlockPos, state: BlockState) : InventoryBlockEntity(ModBlockEntities.MINI_CHEST, pos, state) {
    override fun getDefaultName(): Component = Component.translatable("container.storageoptions.mini_chest")

    override fun getContainerSize(): Int = 1

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player) = MiniChestMenu(containerId, playerInventory, this)
}