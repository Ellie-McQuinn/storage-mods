package quest.toybox.storage.library.client

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import quest.toybox.storage.library.block.misc.ChestType
import quest.toybox.storage.options.EllsSO

object ChestBlockItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer {
    val model by lazy { ChestModel(Minecraft.getInstance().entityModels.bakeLayer(ChestModel.SINGLE_MODEL_LAYER)) }

    override fun render(
        stack: ItemStack, context: ItemDisplayContext, poses: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        model.render(
            Direction.SOUTH, 0.0F, ChestModel.getTexture(EllsSO.id(stack.item), ChestType.SINGLE),
            poses, bufferSource, packedLight, packedOverlay
        )
    }
}