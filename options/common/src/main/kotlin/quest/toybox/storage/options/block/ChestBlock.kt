package quest.toybox.storage.options.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import quest.toybox.storage.options.block.entity.ChestBlockEntity
import quest.toybox.storage.options.registration.ModBlockEntities

class ChestBlock(properties: Properties) : ClassicChestBlock(properties), SimpleWaterloggedBlock {
    override fun getRenderShape(state: BlockState): RenderShape = RenderShape.ENTITYBLOCK_ANIMATED

    override fun newBlockEntity(pos: BlockPos, state: BlockState): ChestBlockEntity? =
        ModBlockEntities.CHEST.create(pos, state)

    override fun codec(): MapCodec<out ChestBlock> = CODEC

    override fun getDoubleContainerName(): Component {
        return Component.translatable("container.chestDouble")
    }

    override fun <T : BlockEntity> getTicker(level: Level, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T>? {
        return createTickerHelper(type, ModBlockEntities.CHEST, ChestBlockEntity::tick)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when(getConnectedDirection(state)) {
            Direction.NORTH -> NORTH_SHAPE
            Direction.EAST -> EAST_SHAPE
            Direction.SOUTH -> SOUTH_SHAPE
            Direction.WEST -> WEST_SHAPE
            else -> SINGLE_SHAPE
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)

        builder.add(WATERLOGGED)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val placingInWater = context.level.getFluidState(context.clickedPos).type === Fluids.WATER

        return super.getStateForPlacement(context)?.setValue(WATERLOGGED, placingInWater)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos)
    }

    companion object {
        val CODEC: MapCodec<out ChestBlock> = simpleCodec(::ChestBlock)

        val SINGLE_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
        val NORTH_SHAPE: VoxelShape = box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0)
        val EAST_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0)
        val SOUTH_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0)
        val WEST_SHAPE: VoxelShape = box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0)
    }
}