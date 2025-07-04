package quest.toybox.storage.library.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions
import quest.toybox.storage.options.EllsSO

object ShulkerBoxItemExtensions : IClientItemExtensions {
    val renderer by lazy {
        val minecraft = Minecraft.getInstance()

        object : BlockEntityWithoutLevelRenderer(minecraft.blockEntityRenderDispatcher, minecraft.entityModels) {
            val model = ShulkerBoxModel(minecraft.entityModels.bakeLayer(ShulkerBoxModel.MODEL_LAYER))

            override fun renderByItem(
                stack: ItemStack, context: ItemDisplayContext, poses: PoseStack,
                bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
            ) {
                model.render(
                    Direction.UP, ShulkerBoxModel.getTexture(EllsSO.id(stack.item)), 0.0F,
                    poses, bufferSource, packedLight, packedOverlay
                )
            }
        }
    }

    override fun getCustomRenderer(): BlockEntityWithoutLevelRenderer {
        return renderer
    }
}