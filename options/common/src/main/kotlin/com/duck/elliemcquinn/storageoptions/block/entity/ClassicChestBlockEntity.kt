package com.duck.elliemcquinn.storageoptions.block.entity

import com.duck.elliemcquinn.storageoptions.block.ClassicChestBlock
import com.duck.elliemcquinn.storageoptions.block.ClassicChestBlock.Companion.CHEST_TYPE
import com.duck.elliemcquinn.storageoptions.block.misc.CachedDoubleBlock
import com.duck.elliemcquinn.storageoptions.block.misc.ChestType
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING

open class ClassicChestBlockEntity<out E : DoubleInventoryBlockEntity<E>>(type: BlockEntityType<*>, pos: BlockPos, state: BlockState) : DoubleInventoryBlockEntity<E>(type, pos, state) {
    constructor(pos: BlockPos, state: BlockState) : this(ModBlockEntities.CLASSIC_CHEST, pos, state)

    override fun getDefaultName(): Component = Component.translatable("container.ellsso.classic_chest")

    override fun ensureDoubleCache(): CachedDoubleBlock<E>? {
        val chestType = blockState.getValue(CHEST_TYPE)

        if (chestType != ChestType.SINGLE) {
            val otherEntity = level!!.getBlockEntity(
                blockPos.relative(ClassicChestBlock.getConnectedDirection(blockState)!!)
            ) as? ClassicChestBlockEntity<E>

            if (otherEntity != null) {
                val doubleCache = if (chestType == ChestType.LEFT) {
                    CachedDoubleBlock(this, otherEntity)
                } else {
                    CachedDoubleBlock(otherEntity, this)
                }

                doubleCache.first.doubleCache = doubleCache as CachedDoubleBlock<E>?
                doubleCache.second.doubleCache = doubleCache

                return doubleCache
            }
        }

        return null
    }

    override fun areStatesMeaningfullyDifferent(first: BlockState, second: BlockState): Boolean {
        return first.getValue(HORIZONTAL_FACING) != second.getValue(HORIZONTAL_FACING) ||
                first.getValue(CHEST_TYPE) != second.getValue(CHEST_TYPE)
    }

    override fun isSingleState(state: BlockState) = state.getValue(CHEST_TYPE) == ChestType.SINGLE
}