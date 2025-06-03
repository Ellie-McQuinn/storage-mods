package quest.toybox.storage.metallum.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.common.data.ExistingFileHelper
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.library.block.AChestBlock
import quest.toybox.storage.library.block.ATallBarrelBlock
import quest.toybox.storage.library.block.misc.BarrelType
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.registration.ModBlocks

class BlockStateProvider(
    output: PackOutput,
    fileHelper: ExistingFileHelper
) : BlockStateProvider(output, EllsSM.MOD_ID, fileHelper) {
    override fun registerStatesAndModels() {
        registerBarrel(ModBlocks.COPPER_BARREL)
        registerBarrel(ModBlocks.EXPOSED_COPPER_BARREL)
        registerBarrel(ModBlocks.WEATHERED_COPPER_BARREL)
        registerBarrel(ModBlocks.OXIDIZED_COPPER_BARREL)
        registerWaxedBarrel(ModBlocks.WAXED_COPPER_BARREL, ModBlocks.COPPER_BARREL)
        registerWaxedBarrel(ModBlocks.WAXED_EXPOSED_COPPER_BARREL, ModBlocks.EXPOSED_COPPER_BARREL)
        registerWaxedBarrel(ModBlocks.WAXED_WEATHERED_COPPER_BARREL, ModBlocks.WEATHERED_COPPER_BARREL)
        registerWaxedBarrel(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL, ModBlocks.OXIDIZED_COPPER_BARREL)
        registerBarrel(ModBlocks.IRON_BARREL)
        registerBarrel(ModBlocks.GOLDEN_BARREL)
        registerBarrel(ModBlocks.DIAMOND_BARREL)
        registerBarrel(ModBlocks.NETHERITE_BARREL)
    }

    fun registerBarrelModels(block: ATallBarrelBlock,
                             singleModel: ModelFile, singleOpenModel: ModelFile,
                             bottomModel: ModelFile,
                             topModel: ModelFile, topOpenModel: ModelFile
    ) {
        directionalBlock(block) { state ->
            val barrelType = state.getValue(ATallBarrelBlock.BARREL_TYPE)

            when (barrelType) {
                BarrelType.SINGLE -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) singleOpenModel else singleModel
                }
                BarrelType.BOTTOM -> bottomModel
                BarrelType.TOP -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) topOpenModel else topModel
                }
            }
        }

        itemModels().withExistingParent(
            EllsSO.id(block).path,
            singleModel.location
        )
    }

    fun registerWaxedBarrel(block: ATallBarrelBlock, parent: ATallBarrelBlock) {
        val parentName = EllsSO.id(parent)

        val singleBarrel = models().getExistingFile(parentName)
        val singleBarrelOpen = models().getExistingFile(parentName.withPath { "${it}_open"})
        val bottomBarrel = models().getExistingFile(parentName.withPath { "${it}_bottom"})
        val topBarrel = models().getExistingFile(parentName.withPath { "${it}_top"})
        val topBarrelOpen = models().getExistingFile(parentName.withPath { "${it}_top_open"})

        registerBarrelModels(block, singleBarrel, singleBarrelOpen, bottomBarrel, topBarrel, topBarrelOpen)
    }

    fun registerBarrel(block: ATallBarrelBlock) {
        val name = EllsSO.id(block).path

        val singleBarrel = models().withExistingParent(name, ResourceLocation.withDefaultNamespace("block/barrel"))
            .texture("side", EllsSM.id("block/${name}_side"))
        val singleBarrelOpen = models().withExistingParent("${name}_open", singleBarrel.location)
            .texture("top", ResourceLocation.withDefaultNamespace("block/barrel_top_open"))

        val bottomBarrel = models().withExistingParent("${name}_bottom", EllsSO.id("block/base/barrel_bottom"))
            .texture("side", EllsSM.id("block/${name}_bottom_side"))

        val topBarrel = models().withExistingParent("${name}_top", EllsSO.id("block/base/barrel_top"))
            .texture("side", EllsSM.id("block/${name}_top_side"))

        val topBarrelOpen = models().withExistingParent("${name}_top_open", topBarrel.location)
            .texture("top", ResourceLocation.withDefaultNamespace("block/barrel_top_open"))

        registerBarrelModels(block, singleBarrel, singleBarrelOpen, bottomBarrel, topBarrel, topBarrelOpen)
    }

    fun registerChest(modelName: String, particle: ResourceLocation, block: AChestBlock) {
        val model = models().getBuilder(modelName).texture("particle", particle)

        getVariantBuilder(block).partialState().setModels(ConfiguredModel(model))

        itemModels().withExistingParent(modelName, ResourceLocation.withDefaultNamespace("item/chest"))
    }
}