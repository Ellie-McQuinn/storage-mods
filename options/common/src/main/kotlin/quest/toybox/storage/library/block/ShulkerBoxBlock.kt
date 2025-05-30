package quest.toybox.storage.library.block

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.monster.Shulker
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import quest.toybox.storage.library.block.entity.ShulkerBoxBlockEntity
import quest.toybox.storage.options.registration.ModBlockEntities
import java.util.*

class ShulkerBoxBlock(val color: DyeColor?, properties: Properties) : InventoryBlock(properties) {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.UP))
    }

    override fun isAccessBlocked(level: Level, state: BlockState, pos: BlockPos): Boolean {
        if (state.hasProperty(FACING)) {
            return !level.noCollision(Shulker.getProgressDeltaAabb(1.0F, state.getValue(FACING), 0.0F, 0.5F).move(pos))
        }

        return true
    }

    override fun codec(): MapCodec<out ShulkerBoxBlock> = CODEC

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): ShulkerBoxBlockEntity? = ModBlockEntities.SHULKER_BOX.create(pos, state)

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return this.defaultBlockState().setValue(FACING, context.clickedFace)
    }

    override fun onRemove(state: BlockState, level: Level, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        if (!state.`is`(newState.block)) {
            level.removeBlockEntity(pos)
            level.updateNeighbourForOutputSignal(pos, state.block)
        }
    }

    override fun playerWillDestroy(level: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        level.getBlockEntity(pos, ModBlockEntities.SHULKER_BOX).ifPresent { entity ->
            if (!level.isClientSide() && player.isCreative && !entity.isEmpty) {
                val stack = asItem().defaultInstance

                stack.applyComponents(entity.collectComponents())

                val item = ItemEntity(level, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, stack)

                item.setDefaultPickUpDelay()
                level.addFreshEntity(item)
            } else {
                entity.unpackLootTable(player)
            }
        }

        return super.playerWillDestroy(level, pos, state, player)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return level.getBlockEntity(pos, ModBlockEntities.SHULKER_BOX).map { entity ->
            Shapes.create(
                Shulker.getProgressAabb(1.0F, entity.blockState.getValue(FACING), 0.5F * entity.getOpenNess(1.0F))
            )
        }.orElse(Shapes.block())
    }

    override fun propagatesSkylightDown(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean = false

    override fun <T : BlockEntity> getTicker(level: Level, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T>? {
        return createTickerHelper(type, ModBlockEntities.SHULKER_BOX, ShulkerBoxBlockEntity::tick)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        val stack = super.getCloneItemStack(level, pos, state)

        level.getBlockEntity(pos, ModBlockEntities.SHULKER_BOX).ifPresent { entity ->
            entity.saveToItem(stack, level.registryAccess())
        }

        return stack
    }

    override fun getDrops(state: BlockState, params: LootParams.Builder): List<ItemStack> {
        val entity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY) as? ShulkerBoxBlockEntity ?:
            return super.getDrops(state, params)

        val params = params.withDynamicDrop(ResourceLocation.withDefaultNamespace("contents")) { thing ->
            for (i in 0 until entity.containerSize) {
                thing.accept(entity.getItem(i))
            }
        }

        return super.getDrops(state, params)
    }

    override fun getBlockSupportShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return level.getBlockEntity(pos, ModBlockEntities.SHULKER_BOX).map { entity ->
            if (entity.getOpenNess(1.0F) == 0.0F) {
                Shapes.block()
            } else {
                BLOCK_SUPPORT_SHAPES[entity.blockState.getValue(FACING).opposite]!!
            }
        }.orElse(Shapes.block())
    }

    companion object {
        val CODEC: MapCodec<ShulkerBoxBlock> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                propertiesCodec(),
                DyeColor.CODEC.optionalFieldOf("color").forGetter { block -> Optional.ofNullable(block.color) },
            ).apply(instance) { properties, color -> ShulkerBoxBlock(color.orElse(null), properties) }
        }

        val BLOCK_SUPPORT_SHAPES = mapOf(
            Direction.NORTH to box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0),
            Direction.EAST to box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Direction.SOUTH to box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0),
            Direction.WEST to box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0),
            Direction.UP to box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            Direction.DOWN to box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
        )
    }
}