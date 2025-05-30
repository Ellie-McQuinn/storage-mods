package quest.toybox.storage.options.client

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import quest.toybox.storage.options.block.misc.ChestType

object ChestBlockItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer {
    val model by lazy { ChestModel(Minecraft.getInstance().entityModels.bakeLayer(ChestModel.SINGLE_MODEL_LAYER)) }

    override fun render(
        stack: ItemStack, context: ItemDisplayContext, poses: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        model.render(
            Direction.SOUTH, 0.0F, ChestModel.getTexture(stack.item.builtInRegistryHolder(), ChestType.SINGLE),
            poses, bufferSource, packedLight, packedOverlay
        )
    }
}