package quest.toybox.storage.metallum.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
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

        directionalBlock(block) { state ->
            val barrelType = state.getValue(ATallBarrelBlock.BARREL_TYPE)

            when (barrelType) {
                BarrelType.SINGLE -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) singleBarrelOpen else singleBarrel
                }
                BarrelType.BOTTOM -> bottomBarrel
                BarrelType.TOP -> {
                    val open = state.getValue(BlockStateProperties.OPEN)

                    if (open) topBarrelOpen else topBarrel
                }
            }
        }

        itemModels().withExistingParent(
            name,
            singleBarrel.location
        )
    }

    fun registerChest(modelName: String, particle: ResourceLocation, block: AChestBlock) {
        val model = models().getBuilder(modelName).texture("particle", particle)

        getVariantBuilder(block).partialState().setModels(ConfiguredModel(model))

        itemModels().withExistingParent(modelName, ResourceLocation.withDefaultNamespace("item/chest"))
    }
}