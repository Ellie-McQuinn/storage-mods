package quest.toybox.storage.options.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.Model
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.resources.model.Material
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.item.DyeColor
import quest.toybox.storage.options.EllsSO

class ShulkerBoxModel(model: ModelPart) : Model(RenderType::entityCutoutNoCull) {
    private val base: ModelPart = model.getChild("base")
    private val lid: ModelPart = model.getChild("lid")

    fun render(
       facing: Direction, color: DyeColor?, openness: Float,
        poses: PoseStack, bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        val texture = when (color) {
            null -> DEFAULT_SHULKER_MATERIAL
            else -> DYED_SHULKER_MATERIALS[color]!!
        }

        poses.pushPose()
        poses.translate(0.5F, 0.5F, 0.5F)
        poses.mulPose(facing.rotation)
        poses.scale(0.9995F, 0.9995F, 0.9995F)

        lid.setPos(0.0F, 12.0F + 8.0F * openness, 0.0F)
        lid.yRot = -270.0F * openness * Mth.DEG_TO_RAD

        val buffer = texture.buffer(bufferSource, RenderType::entityCutoutNoCull)

        renderToBuffer(poses, buffer, packedLight, packedOverlay)

        poses.popPose()
    }

    override fun renderToBuffer(
        poses: PoseStack,
        buffer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        color: Int
    ) {
        base.render(poses, buffer, packedLight, packedOverlay)
        lid.render(poses, buffer, packedLight, packedOverlay)
    }

    companion object {
        val DEFAULT_SHULKER_MATERIAL = Material(Sheets.SHULKER_SHEET, EllsSO.id("entity/shulker/shulker_box"))

        val DYED_SHULKER_MATERIALS = DyeColor.entries.associateWith {
            Material(Sheets.SHULKER_SHEET, EllsSO.id("entity/shulker/${it.serializedName}_shulker_box"))
        }

        val MODEL_LAYER = ModelLayerLocation(EllsSO.id("shulker_box"), "main")

        fun createLayer(): LayerDefinition {
            val mesh = MeshDefinition()

            // Re-created in block bench to fix rotation issues;
            // Please also consider support Block Bench: https://www.blockbench.net/donate/
            mesh.root.addOrReplaceChild(
                "base",
                CubeListBuilder.create().texOffs(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F),
                PartPose.ZERO
            )

            mesh.root.addOrReplaceChild(
                "lid",
                CubeListBuilder.create().addBox(-8.0F, -16.0F, -8.0F, 16.0F, 12.0F, 16.0F),
                PartPose.ZERO
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}