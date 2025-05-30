package quest.toybox.storage.library.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.ContainerHelper
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.platform.CommonHelper

abstract class InventoryBlockEntity(
    type: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState
) : RandomizableContainerBlockEntity(type, pos, state) {
    private var _items: NonNullList<ItemStack> = NonNullList.withSize(containerSize, ItemStack.EMPTY)
    private val _itemAccess: Any by lazy { CommonHelper.INSTANCE.getItemAccess(this) }

    override fun getItems(): NonNullList<ItemStack> = _items

    override fun setItems(items: NonNullList<ItemStack>) {
        this._items = items
    }

    override fun createMenu(syncId: Int, playerInventory: Inventory): AbstractContainerMenu =
        ChestMenu.threeRows(syncId, playerInventory, this)

    override fun getContainerSize(): Int = 27

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)

        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, _items, registries)
        }
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)

        _items = NonNullList.withSize<ItemStack>(containerSize, ItemStack.EMPTY)

        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this._items, registries)
        }
    }

    open fun getItemAccess(): Any = _itemAccess
    fun getOwnAccess(): Any = _itemAccess

    companion object {
        const val DOUBLE_BLOCK_INVALIDATED: Int = 1
        const val VIEWER_COUNT_CHANGED: Int = 2
    }
}