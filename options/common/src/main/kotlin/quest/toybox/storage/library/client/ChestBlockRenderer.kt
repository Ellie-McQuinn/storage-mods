package quest.toybox.storage.library.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import quest.toybox.storage.library.block.ClassicChestBlock
import quest.toybox.storage.library.block.entity.ChestBlockEntity
import quest.toybox.storage.library.block.misc.ChestType
import kotlin.math.max
import kotlin.math.pow

class ChestBlockRenderer(context: BlockEntityRendererProvider.Context) : BlockEntityRenderer<ChestBlockEntity> {
    val singleModel = ChestModel(context.bakeLayer(ChestModel.SINGLE_MODEL_LAYER))
    val leftModel = ChestModel(context.bakeLayer(ChestModel.LEFT_MODEL_LAYER))
    val rightModel = ChestModel(context.bakeLayer(ChestModel.RIGHT_MODEL_LAYER))

    override fun render(
        entity: ChestBlockEntity, partialTick: Float, poses: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        val openness = entity.getDoubleCache()?.let {
            it.calculate { first, second -> max(first.getOpenNess(partialTick), second.getOpenNess(partialTick)) }
        } ?: entity.getOpenNess(partialTick)

        val lidAngle = openness.let { 1 - it }.let { 1 - it.pow(3) }

        val facing = entity.blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)
        val chestType = entity.blockState.getValue(ClassicChestBlock.CHEST_TYPE)

        when (chestType) {
            ChestType.SINGLE -> {
                singleModel.render(
                    facing, lidAngle, ChestModel.getTexture(entity.blockState.block.builtInRegistryHolder(), ChestType.SINGLE),
                    poses, bufferSource, packedLight, packedOverlay
                )
            }
            ChestType.LEFT -> {
                leftModel.render(
                    facing, lidAngle, ChestModel.getTexture(entity.blockState.block.builtInRegistryHolder(), ChestType.LEFT),
                    poses, bufferSource, packedLight, packedOverlay
                )
            }
            ChestType.RIGHT -> {
                rightModel.render(
                    facing, lidAngle, ChestModel.getTexture(entity.blockState.block.builtInRegistryHolder(), ChestType.RIGHT),
                    poses, bufferSource, packedLight, packedOverlay
                )
            }
        }
    }
}