package quest.toybox.storage.options.block.menu

import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import quest.toybox.storage.options.registration.ModMenus

class MiniChestMenu(syncId: Int, playerInventory: Inventory, val container: Container) : AbstractContainerMenu(ModMenus.MINI_CHEST, syncId) {
    constructor(syncId: Int, playerInventory: Inventory): this(syncId, playerInventory, SimpleContainer(1))

    init {
        container.startOpen(playerInventory.player)

        this.addSlot(Slot(container, 0, 80, 35))

        for (y in 0..< 3) {
            for (x in 0..< 9) {
                this.addSlot(Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, y * 18 + 84))
            }
        }

        for (x in 0..< 9) {
            this.addSlot(Slot(playerInventory, x, 8 + x * 18, 142))
        }
    }

    override fun quickMoveStack(player: Player, slot: Int): ItemStack {
        if (slot >= slots.size) {
            return ItemStack.EMPTY
        }

        val target = slots[slot]

        if (!target.hasItem()) {
            return ItemStack.EMPTY
        }

        val stackInSlot = target.item
        val copy = stackInSlot.copy()

        if (slot == 0) {
            if (!moveItemStackTo(stackInSlot, container.containerSize, slots.size, true)) {
                return ItemStack.EMPTY
            }
        } else if (!moveItemStackTo(stackInSlot, 0, container.containerSize, false)) {
            return ItemStack.EMPTY
        }

        if (stackInSlot.isEmpty) {
            target.setByPlayer(ItemStack.EMPTY)
        } else {
            target.setChanged()
        }

        return copy
    }

    override fun stillValid(player: Player): Boolean {
        return container.stillValid(player)
    }
}