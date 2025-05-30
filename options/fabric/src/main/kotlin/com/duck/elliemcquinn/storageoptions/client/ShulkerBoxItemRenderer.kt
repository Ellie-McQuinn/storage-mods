package com.duck.elliemcquinn.storageoptions.client

import com.duck.elliemcquinn.storageoptions.block.ShulkerBoxBlock
import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

object ShulkerBoxItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer {
    val model by lazy { ShulkerBoxModel(Minecraft.getInstance().entityModels.bakeLayer(ShulkerBoxModel.MODEL_LAYER)) }

    override fun render(
        stack: ItemStack, context: ItemDisplayContext, poses: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        model.render(
            Direction.UP, ((stack.item as BlockItem).block as ShulkerBoxBlock).color, 0.0F,
            poses, bufferSource, packedLight, packedOverlay
        )
    }
}