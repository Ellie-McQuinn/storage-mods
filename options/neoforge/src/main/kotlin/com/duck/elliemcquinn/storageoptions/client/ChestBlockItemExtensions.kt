package com.duck.elliemcquinn.storageoptions.client

import com.duck.elliemcquinn.storageoptions.block.misc.ChestType
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions

object ChestBlockItemExtensions : IClientItemExtensions {
    val renderer by lazy {
        val minecraft = Minecraft.getInstance()

        object : BlockEntityWithoutLevelRenderer(minecraft.blockEntityRenderDispatcher, minecraft.entityModels) {
            val model = ChestModel(minecraft.entityModels.bakeLayer(ChestModel.SINGLE_MODEL_LAYER))

            override fun renderByItem(
                stack: ItemStack, context: ItemDisplayContext, poses: PoseStack,
                bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
            ) {
                model.render(
                    Direction.SOUTH, 0.0F, ChestModel.getTexture(stack.item.builtInRegistryHolder(), ChestType.SINGLE),
                    poses, bufferSource, packedLight, packedOverlay
                )
            }
        }
    }

    override fun getCustomRenderer(): BlockEntityWithoutLevelRenderer {
        return renderer
    }
}