package quest.toybox.storage.library.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.monster.Shulker
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ShulkerBoxMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.level.block.entity.LidBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.Vec3
import org.joml.Vector3d
import org.joml.Vector3f
import quest.toybox.storage.library.block.misc.LidController
import quest.toybox.storage.library.block.misc.LidController.AnimationState
import quest.toybox.storage.options.registration.ModBlockEntities

class ShulkerBoxBlockEntity(pos: BlockPos, state: BlockState) : InventoryBlockEntity(ModBlockEntities.SHULKER_BOX, pos, state), LidBlockEntity {
    var lidController = object : LidController() {
        override fun onLidOpenMore(level: Level, pos: BlockPos, state: BlockState) {
            val openDirection = state.getValue(BlockStateProperties.FACING)

            val changedBoundingBox = Shulker.getProgressDeltaAabb(1.0F, openDirection, oldOpenness, openness).move(pos)
            val entities = level.getEntities(null, changedBoundingBox)

            val force = Vec3(
                Vector3d(changedBoundingBox.xsize, changedBoundingBox.ysize, changedBoundingBox.zsize)
                    .add(0.01, 0.01, 0.01)
                    .mul(openDirection.step())
                    .get(Vector3f())
            )

            for (entity in entities) {
                if (entity.pistonPushReaction != PushReaction.IGNORE) {
                    entity.move(MoverType.SHULKER_BOX, force)
                }
            }
        }

        override fun onAnimationStateChanged(level: Level, pos: BlockPos, state: BlockState, animation: AnimationState) {
            state.updateNeighbourShapes(level, pos, 3)
            level.updateNeighborsAt(pos, state.block)
        }
    }

    val openersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
            level.playSound(null, blockPos, SoundEvents.SHULKER_BOX_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F)
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
            level.playSound(null, blockPos, SoundEvents.SHULKER_BOX_CLOSE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F)
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, oldCount: Int, count: Int) {
            level.blockEvent(pos, state.block, VIEWER_COUNT_CHANGED, count)
        }

        override fun isOwnContainer(player: Player): Boolean {
            return (player.containerMenu as? ShulkerBoxMenu)?.container == this
        }
    }

    override fun getDefaultName(): Component {
        return Component.translatable("container.shulkerBox")
    }

    override fun getOpenNess(partialTicks: Float): Float {
        return lidController.getOpenNess(partialTicks)
    }

    fun updateAnimation(level: Level, pos: BlockPos, state: BlockState) {
        lidController.tickLid(level, pos, state)
    }

    override fun createMenu(syncId: Int, playerInventory: Inventory): AbstractContainerMenu {
        return ShulkerBoxMenu(syncId, playerInventory, this)
    }

    override fun startOpen(player: Player) {
        if (isRemoved || player.isSpectator) return
        val level = level ?: return

        openersCounter.incrementOpeners(player, level, blockPos, blockState)
    }

    override fun stopOpen(player: Player) {
        if (isRemoved || player.isSpectator) return
        val level = level ?: return

        openersCounter.decrementOpeners(player, level, blockPos, blockState)
    }

    override fun triggerEvent(id: Int, type: Int): Boolean {
        if (id == VIEWER_COUNT_CHANGED) {
            lidController.setOpen(type > 0)

            return true
        }

        return super.triggerEvent(id, type)
    }

    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return stack.item.canFitInsideContainerItems()
    }

    companion object {
        fun tick(level: Level, pos: BlockPos, state: BlockState, entity: ShulkerBoxBlockEntity) {
            entity.updateAnimation(level, pos, state)
        }

        fun isClosed(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
            return level.getBlockEntity(pos, ModBlockEntities.SHULKER_BOX).map { entity ->
                entity.lidController.getAnimationState() == AnimationState.CLOSED
            }.orElse(true)
        }
    }
}