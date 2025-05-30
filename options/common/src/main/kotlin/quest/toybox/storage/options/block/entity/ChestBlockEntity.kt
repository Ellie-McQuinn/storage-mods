package quest.toybox.storage.options.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.CompoundContainer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.level.block.entity.LidBlockEntity
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.block.ClassicChestBlock
import quest.toybox.storage.options.block.misc.ChestType
import quest.toybox.storage.options.block.misc.LidController
import quest.toybox.storage.options.registration.ModBlockEntities

class ChestBlockEntity(pos: BlockPos, state: BlockState) : ClassicChestBlockEntity<ChestBlockEntity>(ModBlockEntities.CHEST, pos, state), LidBlockEntity {
    val lidController = LidController()

    val openersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
            playSound(level, pos, state, SoundEvents.CHEST_OPEN)
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
            playSound(level, pos, state, SoundEvents.CHEST_CLOSE)
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, oldCount: Int, count: Int) {
            level.blockEvent(pos, state.block, VIEWER_COUNT_CHANGED, count)
        }

        override fun isOwnContainer(player: Player): Boolean {
            val menu = player.containerMenu as? ChestMenu ?: return false

            if (menu.container == this) {
                return true
            }

            val doubleContainer = menu.container as? CompoundContainer ?: return false

            return doubleContainer.contains(this@ChestBlockEntity)
        }

    }

    override fun getDefaultName(): Component = Component.translatable("container.chest")

    override fun getOpenNess(partialTicks: Float): Float {
        return lidController.getOpenNess(partialTicks)
    }

    fun updateAnimation(level: Level, pos: BlockPos, state: BlockState) {
        lidController.tickLid(level, pos, state)
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

    companion object {
        fun tick(level: Level, pos: BlockPos, state: BlockState, entity: ChestBlockEntity) {
            if (level.isClientSide()) {
                entity.updateAnimation(level, pos, state)
            }
        }

        fun playSound(level: Level, pos: BlockPos, state: BlockState, sound: SoundEvent) {
            val type = state.getValue(ClassicChestBlock.CHEST_TYPE)

            if (type == ChestType.RIGHT) {
                return
            }

            val soundPos = if (type == ChestType.SINGLE) {
                pos.center
            } else {
                pos.center.relative(ClassicChestBlock.getConnectedDirection(state)!!, 0.5)
            }.add(0.0, 0.5, 0.0)

            level.playSound(null, soundPos.x, soundPos.y, soundPos.z, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F)
        }
    }
}