package quest.toybox.storage.library.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
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
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.library.block.misc.ChestType
import quest.toybox.storage.options.registration.ModBlocks

class ChestModel(model: ModelPart) : Model(RenderType::entityCutoutNoCull) {
    private val base: ModelPart = model.getChild("base")
    private val lid: ModelPart = model.getChild("lid")

    fun render(
        facing: Direction, openness: Float, texture: Material,
        poses: PoseStack, bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        poses.pushPose()

        poses.translate(0.5F, 0.0F, 0.5F)
        poses.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()))

        lid.xRot = -openness * Mth.HALF_PI

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
        val TEXTURES: Map<ResourceLocation, Map<ChestType, Material>> = mapOf(
            ModBlocks.OAK_CHEST.builtInRegistryHolder().key().location() to mapOf(
                ChestType.SINGLE to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/oak_single")),
                ChestType.LEFT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/oak_left")),
                ChestType.RIGHT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/oak_right"))
            ),
            ModBlocks.SPRUCE_CHEST.builtInRegistryHolder().key().location() to mapOf(
                ChestType.SINGLE to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/spruce_single")),
                ChestType.LEFT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/spruce_left")),
                ChestType.RIGHT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/spruce_right"))
            ),
            ModBlocks.DARK_OAK_CHEST.builtInRegistryHolder().key().location() to mapOf(
                ChestType.SINGLE to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/dark_oak_single")),
                ChestType.LEFT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/dark_oak_left")),
                ChestType.RIGHT to Material(Sheets.CHEST_SHEET, EllsSO.id("entity/chest/dark_oak_right"))
            )
        )

        val SINGLE_MODEL_LAYER = ModelLayerLocation(EllsSO.id("chest"), "single")
        val LEFT_MODEL_LAYER = ModelLayerLocation(EllsSO.id("chest"), "left")
        val RIGHT_MODEL_LAYER = ModelLayerLocation(EllsSO.id("chest"), "right")

        fun createSingleLayer(): LayerDefinition {
            val mesh = MeshDefinition().apply {
                root.addOrReplaceChild(
                    "base",
                    CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 10.0F, 14.0F),
                    PartPose.ZERO
                )

                root.addOrReplaceChild(
                    "lid",
                    CubeListBuilder.create()
                        .addBox(-7.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F)
                        .addBox(-1.0F, -2.0F, 14.0F, 2.0F, 4.0F, 1.0F),
                    PartPose.offset(0.0F, 9.0F, -7.0F)
                )
            }

            return LayerDefinition.create(mesh, 64, 64)
        }

        fun createLeftLayer(): LayerDefinition {
            val mesh = MeshDefinition().apply {
                root.addOrReplaceChild(
                    "base",
                    CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, 0.0F, -7.0F, 15.0F, 10.0F, 14.0F),
                    PartPose.ZERO
                )

                root.addOrReplaceChild(
                    "lid",
                    CubeListBuilder.create()
                        .addBox(-7.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F)
                        .addBox(7.0F, -2.0F, 14.0F, 1.0F, 4.0F, 1.0F),
                    PartPose.offset(0.0F, 9.0F, -7.0F)
                )
            }

            return LayerDefinition.create(mesh, 64, 64)
        }

        fun createRightLayer(): LayerDefinition {
            val mesh = MeshDefinition().apply {
                root.addOrReplaceChild(
                    "base",
                    CubeListBuilder.create().texOffs(0, 19).addBox(-8.0F, 0.0F, -7.0F, 15.0F, 10.0F, 14.0F),
                    PartPose.ZERO
                )

                root.addOrReplaceChild(
                    "lid",
                    CubeListBuilder.create()
                        .addBox(-8.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F)
                        .addBox(-8.0F, -2.0F, 14.0F, 1.0F, 4.0F, 1.0F),
                    PartPose.offset(0.0F, 9.0F, -7.0F)
                )
            }

            return LayerDefinition.create(mesh, 64, 64)
        }

        fun getTexture(reference: Holder.Reference<*>, chestType: ChestType): Material {
            return TEXTURES[reference.key().location()]!![chestType]!!
        }
    }
}