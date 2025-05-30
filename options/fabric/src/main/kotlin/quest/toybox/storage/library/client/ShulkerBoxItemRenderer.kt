package quest.toybox.storage.library.client

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import quest.toybox.storage.library.block.ShulkerBoxBlock

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