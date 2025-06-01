package quest.toybox.storage.library.block.entity

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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING
import net.minecraft.world.level.block.state.properties.BlockStateProperties.OPEN
import quest.toybox.storage.library.block.ATallBarrelBlock
import quest.toybox.storage.library.block.ATallBarrelBlock.Companion.BARREL_TYPE
import quest.toybox.storage.library.block.misc.BarrelType
import quest.toybox.storage.library.block.misc.CachedDoubleBlock
import quest.toybox.storage.options.registration.ModBlockEntities

class TallBarrelBlockEntity(pos: BlockPos, state: BlockState) : DoubleInventoryBlockEntity<TallBarrelBlockEntity>(ModBlockEntities.BARREL, pos, state) {
    private val openersCounter: ContainerOpenersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
            if (state.getValue(BARREL_TYPE) != BarrelType.BOTTOM) {
                playLidSound(level, state, pos, SoundEvents.BARREL_OPEN)
                updateOpenState(level, state, pos, true)
            }
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
            if (state.getValue(BARREL_TYPE) != BarrelType.BOTTOM) {
                playLidSound(level, state, pos, SoundEvents.BARREL_CLOSE)
                updateOpenState(level, state, pos, false)
            }
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, oldCount: Int, newCount: Int) {

        }

        override fun isOwnContainer(player: Player): Boolean {
            val container = (player.containerMenu as? ChestMenu ?: return false).container
            return container === this@TallBarrelBlockEntity || container is CompoundContainer && container.contains(this@TallBarrelBlockEntity)
        }
    }

    override fun getDefaultName(): Component = Component.translatable("container.barrel")

    override fun startOpen(player: Player) {
        if (isRemoved || player.isSpectator) return

        openersCounter.incrementOpeners(player, level!!, blockPos, blockState)
    }

    override fun stopOpen(player: Player) {
        if (isRemoved || player.isSpectator) return

        openersCounter.decrementOpeners(player, level!!, blockPos, blockState)
    }

    fun recheckOpen() {
        if (isRemoved) return

        openersCounter.recheckOpeners(level!!, blockPos, blockState)
    }

    override fun ensureDoubleCache(): CachedDoubleBlock<TallBarrelBlockEntity>? {
        val barrelType = blockState.getValue(BARREL_TYPE)

        if (barrelType != BarrelType.SINGLE) {
            val otherEntity = level!!.getBlockEntity(blockPos.relative(ATallBarrelBlock.getConnectedDirection(blockState)!!)) as? TallBarrelBlockEntity

            if (otherEntity != null) {
                val doubleCache = if (barrelType == BarrelType.TOP) {
                    CachedDoubleBlock(this, otherEntity)
                } else {
                    CachedDoubleBlock(otherEntity, this)
                }

                doubleCache.first.doubleCache = doubleCache
                doubleCache.second.doubleCache = doubleCache

                return doubleCache
            }
        }

        return null
    }

    override fun areStatesMeaningfullyDifferent(first: BlockState, second: BlockState): Boolean {
        return first.getValue(FACING) != second.getValue(FACING) ||
                first.getValue(BARREL_TYPE) != second.getValue(BARREL_TYPE)
    }

    override fun isSingleState(state: BlockState) = state.getValue(BARREL_TYPE) == BarrelType.SINGLE

    companion object {
        fun updateOpenState(level: Level, state: BlockState, pos: BlockPos, open: Boolean) {
            level.setBlock(pos, state.setValue(OPEN, open), 3)
        }

        fun playLidSound(level: Level, state: BlockState, pos: BlockPos, sound: SoundEvent) {
            val lidPos = pos.center.relative(state.getValue(FACING), 0.5)
            val pitch = level.random.nextFloat() * 0.1F + 0.9F

            level.playSound(null, lidPos.x, lidPos.y, lidPos.z, sound, SoundSource.BLOCKS, 0.5F, pitch)
        }
    }
}