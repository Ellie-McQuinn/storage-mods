package quest.toybox.storage.options.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.block.misc.CachedDoubleBlock
import quest.toybox.storage.options.platform.CommonHelper

abstract class DoubleInventoryBlockEntity<out E : DoubleInventoryBlockEntity<E>>(
    type: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState
) : InventoryBlockEntity(type, pos, state) {
    internal var doubleCache: CachedDoubleBlock<@UnsafeVariance E>? = null

    protected abstract fun ensureDoubleCache(): CachedDoubleBlock<E>?

    fun getDoubleCache(): CachedDoubleBlock<E>? = doubleCache ?: ensureDoubleCache()?.also { doubleCache = it }

    override fun getItemAccess(): Any = getDoubleCache()?.itemAccess ?: super.getOwnAccess()

    protected abstract fun areStatesMeaningfullyDifferent(first: BlockState, second: BlockState): Boolean

    protected abstract fun isSingleState(state: BlockState): Boolean

    @Deprecated("Deprecated in Java")
    override fun setBlockState(state: BlockState) {
        val oldState = blockState

        @Suppress("DEPRECATION")
        super.setBlockState(state)

        if (areStatesMeaningfullyDifferent(oldState, state)) {
            if (isSingleState(state)) {
                doubleCache?.let {
                    it.first.doubleCache = null
                    it.second.doubleCache = null
                }
            }

            level?.let {
                CommonHelper.INSTANCE.invalidateCapabilities(it, blockPos)

                it.updateNeighbourForOutputSignal(blockPos, state.block)

                it.blockEvent(blockPos, state.block, DOUBLE_BLOCK_INVALIDATED, 0)
            }
        }
    }

    override fun triggerEvent(id: Int, type: Int): Boolean {
        if (id == DOUBLE_BLOCK_INVALIDATED) {
            doubleCache = null

            return true
        }

        return super.triggerEvent(id, type)
    }
}