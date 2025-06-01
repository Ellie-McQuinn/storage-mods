package quest.toybox.storage.options.datagen

import net.minecraft.core.Direction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
import net.neoforged.neoforge.common.data.ExistingFileHelper
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.library.block.AChestBlock
import quest.toybox.storage.library.block.AClassicChestBlock
import quest.toybox.storage.library.block.ATallBarrelBlock
import quest.toybox.storage.library.block.misc.BarrelType
import quest.toybox.storage.library.block.misc.ChestType
import quest.toybox.storage.options.registration.ModBlocks
import quest.toybox.storage.options.registration.ModItems

class BlockStateProvider(
    output: PackOutput,
    fileHelper: ExistingFileHelper
) : BlockStateProvider(output, EllsSO.MOD_ID, fileHelper) {
    override fun registerStatesAndModels() {
        registerBarrel()
        registerClassicChest()
        registerShulkerBoxes()
        registerMiniChest()
        registerChests()
    }

    fun registerBarrel() {
        val vanillaBarrel = models().getExistingFile(ModelLocationUtils.getModelLocation(Blocks.BARREL))
        val openVanillaBarrel = models().getExistingFile(ModelLocationUtils.getModelLocation(Blocks.BARREL, "_open"))

        val bottomBarrel = models().withExistingParent("barrel_bottom", EllsSO.id("block/base/barrel_bottom"))
            .texture("side", EllsSO.id("block/barrel_bottom_side"))

        val topBarrel = models().withExistingParent("barrel_top", EllsSO.id("block/base/barrel_top"))
            .texture("side", EllsSO.id("block/barrel_top_side"))

        val topBarrelOpen = models().withExistingParent("barrel_top_open", topBarrel.location)
            .texture("top", ResourceLocation.withDefaultNamespace("block/barrel_top_open"))

        directionalBlock(ModBlocks.BARREL) { state ->
            val barrelType = state.getValue(ATallBarrelBlock.BARREL_TYPE)

            when (barrelType) {
                BarrelType.SINGLE -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) openVanillaBarrel else vanillaBarrel
                }
                BarrelType.BOTTOM -> bottomBarrel
                BarrelType.TOP -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) topBarrelOpen else topBarrel
                }
            }
        }

        itemModels().withExistingParent(
            BuiltInRegistries.ITEM.getKey(ModItems.BARREL).toString(),
            ModelLocationUtils.getModelLocation(Blocks.BARREL)
        )
    }

    fun registerClassicChest() {
        val singleChest = models().withExistingParent("classic_chest_single", EllsSO.id("block/base/classic_chest_single"))
            .texture("side", EllsSO.id("block/classic_chest_side"))
            .texture("front", EllsSO.id("block/classic_chest_single_front"))
            .texture("top", EllsSO.id("block/classic_chest_single_top"))
            .texture("bottom", EllsSO.id("block/classic_chest_single_bottom"))

        val leftChest = models().withExistingParent("classic_chest_left", EllsSO.id("block/base/classic_chest_left"))
            .texture("side", EllsSO.id("block/classic_chest_side"))
            .texture("front", EllsSO.id("block/classic_chest_left_front"))
            .texture("back", EllsSO.id("block/classic_chest_left_back"))
            .texture("top", EllsSO.id("block/classic_chest_left_top"))
            .texture("bottom", EllsSO.id("block/classic_chest_left_bottom"))

        val rightChest = models().withExistingParent("classic_chest_right", EllsSO.id("block/base/classic_chest_right"))
            .texture("side", EllsSO.id("block/classic_chest_side"))
            .texture("front", EllsSO.id("block/classic_chest_right_front"))
            .texture("back", EllsSO.id("block/classic_chest_right_back"))
            .texture("top", EllsSO.id("block/classic_chest_right_top"))
            .texture("bottom", EllsSO.id("block/classic_chest_right_bottom"))

        getVariantBuilder(ModBlocks.CLASSIC_CHEST).forAllStates { state ->
            val chestType = state.getValue(AClassicChestBlock.CHEST_TYPE)
            val facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)

            ConfiguredModel.builder().apply {
                when (chestType) {
                    ChestType.SINGLE -> modelFile(singleChest)
                    ChestType.LEFT -> modelFile(leftChest)
                    ChestType.RIGHT -> modelFile(rightChest)
                }

                if (facing == Direction.EAST) { rotationY(90) }
                else if (facing == Direction.SOUTH) { rotationY(180) }
                else if (facing == Direction.WEST) { rotationY(270) }
            }.build()
        }

        itemModels().withExistingParent(
            BuiltInRegistries.ITEM.getKey(ModItems.CLASSIC_CHEST).toString(),
            singleChest.location
        )
    }

    fun registerShulkerBoxes() {
        val shulkers = ModItems.SHULKER_BOXES.associateBy { it.block }

        for ((block, item) in shulkers) {
            val name = BuiltInRegistries.BLOCK.getKey(block).path

            val blockModel = models().getBuilder(
                "${EllsSO.MOD_ID}:$name"
            ).texture("particle", EllsSO.id("block/$name"))

            getVariantBuilder(block).partialState().setModels(ConfiguredModel(blockModel))

            itemModels().withExistingParent(
                "${EllsSO.MOD_ID}:$name",
                "minecraft:item/template_shulker_box"
            ).texture("particle", EllsSO.id("block/$name"))
        }
    }

    fun registerMiniChest() {
        val model = models().withExistingParent("mini_chest", EllsSO.id("block/base/mini_chest"))
            .texture("texture", EllsSO.id("block/mini_chest"))
            .texture("particle", EllsSO.id("block/classic_chest_single_front"))

        getVariantBuilder(ModBlocks.MINI_CHEST).forAllStatesExcept({ state ->
            ConfiguredModel.builder()
                .modelFile(model)
                .rotationY((state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot().toInt() + 180) % 360)
                .build()
        }, BlockStateProperties.WATERLOGGED)

        itemModels().simpleBlockItem(ModBlocks.MINI_CHEST)
    }

    fun registerChests() {
        registerChest("oak_chest", TextureMapping.getBlockTexture(Blocks.OAK_PLANKS), ModBlocks.OAK_CHEST)
        registerChest("spruce_chest", TextureMapping.getBlockTexture(Blocks.SPRUCE_PLANKS), ModBlocks.SPRUCE_CHEST)
        registerChest("dark_oak_chest", TextureMapping.getBlockTexture(Blocks.DARK_OAK_PLANKS), ModBlocks.DARK_OAK_CHEST)
    }

    fun registerChest(modelName: String, particle: ResourceLocation, block: AChestBlock) {
        val model = models().getBuilder(modelName).texture("particle", particle)

        getVariantBuilder(block).partialState().setModels(ConfiguredModel(model))

        itemModels().withExistingParent(modelName, ResourceLocation.withDefaultNamespace("item/chest"))
    }
}