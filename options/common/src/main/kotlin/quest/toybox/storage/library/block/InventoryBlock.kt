package quest.toybox.storage.library.block

import net.minecraft.core.BlockPos
import net.minecraft.world.Containers
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.monster.piglin.PiglinAi
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import org.jetbrains.annotations.MustBeInvokedByOverriders
import quest.toybox.storage.library.block.entity.InventoryBlockEntity

abstract class InventoryBlock(properties: Properties) : BaseEntityBlock(properties) {
    open fun isAccessBlocked(level: Level, state: BlockState, pos: BlockPos): Boolean = false
    override fun getRenderShape(state: BlockState) = RenderShape.MODEL
    override fun hasAnalogOutputSignal(state: BlockState) = true

    override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos): Int {
        val entity = level.getBlockEntity(pos) as? InventoryBlockEntity ?: return 0

        return AbstractContainerMenu.getRedstoneSignalFromContainer(entity)
    }

    @MustBeInvokedByOverriders
    open fun onOpen(player: Player) = PiglinAi.angerNearbyPiglins(player, true)

    override fun useWithoutItem(state: BlockState, level: Level, pos: BlockPos, player: Player, hit: BlockHitResult): InteractionResult {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS
        }

        this.getMenuProvider(state, level, pos)?.let {
            player.openMenu(it)
            this.onOpen(player)
        }

        return InteractionResult.CONSUME
    }

    override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider? {
        if (isAccessBlocked(level, state, pos)) {
            return null
        }

        return super.getMenuProvider(state, level, pos)
    }

    override fun onRemove(state: BlockState, level: Level, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        Containers.dropContentsOnDestroy(state, newState, level, pos)

        super.onRemove(state, level, pos, newState, isMoving)
    }
}