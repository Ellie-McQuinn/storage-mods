package com.duck.elliemcquinn.storageoptions.block

import com.duck.elliemcquinn.storageoptions.block.entity.ClassicChestBlockEntity
import com.duck.elliemcquinn.storageoptions.block.misc.ChestType
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.stats.Stats
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.ChestBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING
import net.minecraft.world.level.block.state.properties.EnumProperty

open class ClassicChestBlock(properties: Properties) : DoubleInventoryBlock(properties) {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(HORIZONTAL_FACING, Direction.NORTH)
                .setValue(CHEST_TYPE, ChestType.SINGLE)
        )
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        var chestType = ChestType.SINGLE
        var chestFacing = context.horizontalDirection.opposite

        if (context.isSecondaryUseActive) {
            val offset = context.clickedFace.opposite

            if (offset.axis.isHorizontal) {
                val clickedState = context.level.getBlockState(context.clickedPos.relative(offset))

                if (clickedState.`is`(this) && clickedState.getValue(CHEST_TYPE) == ChestType.SINGLE) {
                    val clickedFacing = clickedState.getValue(HORIZONTAL_FACING)

                    if (clickedFacing.clockWise == offset) {
                        chestType = ChestType.RIGHT
                        chestFacing = clickedFacing
                    } else if (clickedFacing.counterClockWise == offset) {
                        chestType = ChestType.LEFT
                        chestFacing = clickedFacing
                    }
                }
            }
        } else {
            val leftState = context.level.getBlockState(context.clickedPos.relative(chestFacing.clockWise))

            if (leftState.`is`(this) && leftState.getValue(CHEST_TYPE) == ChestType.SINGLE && leftState.getValue(HORIZONTAL_FACING) == chestFacing) {
                chestType = ChestType.RIGHT
            } else {
                val rightState = context.level.getBlockState(context.clickedPos.relative(chestFacing.counterClockWise))

                if (rightState.`is`(this) && rightState.getValue(CHEST_TYPE) == ChestType.SINGLE && rightState.getValue(HORIZONTAL_FACING) == chestFacing) {
                    chestType = ChestType.LEFT
                }
            }
        }

        return this.defaultBlockState().setValue(CHEST_TYPE, chestType).setValue(HORIZONTAL_FACING, chestFacing)
    }

    override fun updateShape(
        state: BlockState, direction: Direction,
        neighborState: BlockState, level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (neighborState.`is`(this) && direction.axis.isHorizontal) {
            val chestType = neighborState.getValue(CHEST_TYPE)
            val facing = neighborState.getValue(HORIZONTAL_FACING)
            if (
                state.getValue(CHEST_TYPE) == ChestType.SINGLE &&
                chestType != ChestType.SINGLE &&
                state.getValue(HORIZONTAL_FACING) == facing &&
                direction == getConnectedDirection(chestType, facing)?.opposite
            ) {
                return state.setValue(CHEST_TYPE, if (chestType == ChestType.LEFT) ChestType.RIGHT else ChestType.LEFT)
            }
        } else if (getConnectedDirection(state) == direction) {
            return state.setValue(CHEST_TYPE, ChestType.SINGLE)
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos)
    }

    override fun getDoubleContainerName(): Component = Component.translatable("container.storageoptions.large_classic_chest")
    override fun codec(): MapCodec<out ClassicChestBlock> = CODEC
    override fun newBlockEntity(pos: BlockPos, state: BlockState): ClassicChestBlockEntity<*>? = ModBlockEntities.CLASSIC_CHEST.create(pos, state)

    override fun isAccessBlocked(level: Level, state: BlockState, pos: BlockPos) : Boolean {
        val abovePos = pos.relative(Direction.UP)
        val aboveState = level.getBlockState(abovePos)

        if (aboveState.isRedstoneConductor(level, abovePos) && aboveState.getMenuProvider(level, abovePos) == null) {
            return true
        }

        return ChestBlock.isCatSittingOnChest(level, pos)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(HORIZONTAL_FACING, CHEST_TYPE)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(HORIZONTAL_FACING, rotation.rotate(state.getValue(HORIZONTAL_FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(HORIZONTAL_FACING, mirror.mirror(state.getValue(HORIZONTAL_FACING)))
    }

    override fun onOpen(player: Player) {
        super.onOpen(player)

        player.awardStat(Stats.OPEN_CHEST)
    }

    companion object {
        val CHEST_TYPE: EnumProperty<ChestType> = EnumProperty.create("type", ChestType::class.java)
        val CODEC: MapCodec<out ClassicChestBlock> = simpleCodec(::ClassicChestBlock)

        fun getConnectedDirection(state: BlockState): Direction? {
            return getConnectedDirection(state.getValue(CHEST_TYPE), state.getValue(HORIZONTAL_FACING))
        }

        fun getConnectedDirection(type: ChestType, facing: Direction): Direction? {
            return when (type) {
                ChestType.LEFT -> facing.counterClockWise
                ChestType.RIGHT -> facing.clockWise
                else -> null
            }
        }
    }
}