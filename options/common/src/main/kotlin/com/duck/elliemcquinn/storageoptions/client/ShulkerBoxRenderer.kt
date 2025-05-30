package com.duck.elliemcquinn.storageoptions.client

import com.duck.elliemcquinn.storageoptions.block.entity.ShulkerBoxBlockEntity
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.world.level.block.state.properties.BlockStateProperties

class ShulkerBoxRenderer(context: BlockEntityRendererProvider.Context) : BlockEntityRenderer<ShulkerBoxBlockEntity> {
    val model = ShulkerBoxModel(context.bakeLayer(ShulkerBoxModel.MODEL_LAYER))

    override fun render(
        entity: ShulkerBoxBlockEntity, partialTick: Float, poses: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        val facing = entity.blockState.getValue(BlockStateProperties.FACING)

        model.render(
            facing, entity.color, entity.getOpenNess(partialTick),
            poses, bufferSource, packedLight, packedOverlay
        )
    }
}