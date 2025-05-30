package com.duck.elliemcquinn.storageoptions.block

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import com.duck.elliemcquinn.storageoptions.block.misc.BarrelType
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING
import net.minecraft.world.level.block.state.properties.BlockStateProperties.OPEN
import net.minecraft.world.level.block.state.properties.EnumProperty

class TallBarrelBlock(properties: Properties) : DoubleInventoryBlock(properties) {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(BARREL_TYPE, BarrelType.SINGLE)
        )
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val level = context.level

        if (context.isSecondaryUseActive) {
            val clickedBlock = level.getBlockState(context.clickedPos.relative(context.clickedFace.opposite))

            if (
                clickedBlock.`is`(this) &&
                clickedBlock.getValue(BARREL_TYPE) == BarrelType.SINGLE &&
                clickedBlock.getValue(FACING) == context.clickedFace
            ) {
                return defaultBlockState().setValue(FACING, context.clickedFace).setValue(BARREL_TYPE, BarrelType.TOP)
            }

            return defaultBlockState().setValue(FACING, context.nearestLookingDirection.opposite)
        } else {
            val facing = context.nearestLookingDirection.opposite
            val placingOnBlock = level.getBlockState(context.clickedPos.relative(context.nearestLookingDirection))
            val inversePlacingOnBlock = level.getBlockState(context.clickedPos.relative(facing))

            val barrelType = if (
                placingOnBlock.`is`(this) &&
                placingOnBlock.getValue(FACING) == facing &&
                placingOnBlock.getValue(BARREL_TYPE) == BarrelType.SINGLE
            ) {
                BarrelType.TOP
            } else if(
                inversePlacingOnBlock.`is`(this) &&
                inversePlacingOnBlock.getValue(FACING) == facing &&
                inversePlacingOnBlock.getValue(BARREL_TYPE) == BarrelType.SINGLE
            ) {
                BarrelType.BOTTOM
            } else {
                BarrelType.SINGLE
            }

            return defaultBlockState().setValue(FACING, facing).setValue(BARREL_TYPE, barrelType)
        }
    }

    override fun updateShape(
        state: BlockState, direction: Direction,
        neighborState: BlockState, level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (neighborState.`is`(this)) {
            val partnerType = neighborState.getValue(BARREL_TYPE)
            val partnerFacing = neighborState.getValue(FACING)

            if (state.getValue(BARREL_TYPE) == BarrelType.SINGLE) {
                if (partnerType == BarrelType.TOP && partnerFacing == direction) {
                    return state.setValue(BARREL_TYPE, BarrelType.BOTTOM)
                } else if (partnerType == BarrelType.BOTTOM && partnerFacing.opposite == direction) {
                    return state.setValue(BARREL_TYPE, BarrelType.TOP)
                }
            }
        } else if (getConnectedDirection(state) == direction){
            return state.setValue(BARREL_TYPE, BarrelType.SINGLE)
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos)
    }

    override fun getDoubleContainerName(): Component = Component.translatable("container.storageoptions.barrel")
    override fun newBlockEntity(pos: BlockPos, state: BlockState): TallBarrelBlockEntity? = ModBlockEntities.BARREL.create(pos, state)
    override fun codec(): MapCodec<out TallBarrelBlock> = CODEC

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, OPEN, BARREL_TYPE)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    override fun onOpen(player: Player) {
        super.onOpen(player)

        player.awardStat(Stats.OPEN_BARREL)
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val entity = level.getBlockEntity(pos) as? TallBarrelBlockEntity ?: return

        entity.recheckOpen()
    }

    companion object {
        val BARREL_TYPE: EnumProperty<BarrelType> = EnumProperty.create("type", BarrelType::class.java)
        val CODEC: MapCodec<out TallBarrelBlock> = simpleCodec(::TallBarrelBlock)

        fun getConnectedDirection(state: BlockState): Direction? {
            val type = state.getValue(BARREL_TYPE)
            val facing = state.getValue(FACING)

            return when (type) {
                BarrelType.TOP -> facing.opposite
                BarrelType.BOTTOM -> facing
                else -> null
            }
        }
    }
}