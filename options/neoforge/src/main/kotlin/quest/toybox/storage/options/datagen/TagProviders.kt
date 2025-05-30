package quest.toybox.storage.options.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.options.registration.ModBlocks
import quest.toybox.storage.options.registration.ModItems
import quest.toybox.storage.options.registration.ModTags
import java.util.concurrent.CompletableFuture

class CommonBlockTags(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    fileHelper: ExistingFileHelper
) : BlockTagsProvider(output, registries, EllsSO.MOD_ID, fileHelper) {
    override fun addTags(registries: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlocks.BARREL)
            .add(ModBlocks.CLASSIC_CHEST)
            .add(ModBlocks.MINI_CHEST)
            .add(*ModBlocks.CHESTS)

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(*ModBlocks.SHULKER_BOXES)

        tag(BlockTags.GUARDED_BY_PIGLINS)
            .add(ModBlocks.BARREL)
            .add(ModBlocks.CLASSIC_CHEST)
            .add(*ModBlocks.SHULKER_BOXES)
            .add(ModBlocks.MINI_CHEST)
            .add(*ModBlocks.CHESTS)

        tag(Tags.Blocks.BARRELS_WOODEN)
            .add(ModBlocks.BARREL)

        tag(Tags.Blocks.CHESTS_WOODEN)
            .add(ModBlocks.CLASSIC_CHEST)
            .add(*ModBlocks.CHESTS)

        tag(BlockTags.SHULKER_BOXES)
            .add(*ModBlocks.SHULKER_BOXES)

        for ((color, block) in ModBlocks.DYED_SHULKER_BOXES) {
            when (color) {
                DyeColor.WHITE -> tag(Tags.Blocks.DYED_WHITE).add(block)
                DyeColor.ORANGE -> tag(Tags.Blocks.DYED_ORANGE).add(block)
                DyeColor.MAGENTA -> tag(Tags.Blocks.DYED_MAGENTA).add(block)
                DyeColor.LIGHT_BLUE -> tag(Tags.Blocks.DYED_LIGHT_BLUE).add(block)
                DyeColor.YELLOW -> tag(Tags.Blocks.DYED_YELLOW).add(block)
                DyeColor.LIME -> tag(Tags.Blocks.DYED_LIME).add(block)
                DyeColor.PINK -> tag(Tags.Blocks.DYED_PINK).add(block)
                DyeColor.GRAY -> tag(Tags.Blocks.DYED_GRAY).add(block)
                DyeColor.LIGHT_GRAY -> tag(Tags.Blocks.DYED_LIGHT_GRAY).add(block)
                DyeColor.CYAN -> tag(Tags.Blocks.DYED_CYAN).add(block)
                DyeColor.PURPLE -> tag(Tags.Blocks.DYED_PURPLE).add(block)
                DyeColor.BLUE -> tag(Tags.Blocks.DYED_BLUE).add(block)
                DyeColor.BROWN -> tag(Tags.Blocks.DYED_BROWN).add(block)
                DyeColor.GREEN -> tag(Tags.Blocks.DYED_GREEN).add(block)
                DyeColor.RED -> tag(Tags.Blocks.DYED_RED).add(block)
                DyeColor.BLACK -> tag(Tags.Blocks.DYED_BLACK).add(block)
            }
        }
    }

    override fun getName() = "Block Tags"
}

class CommonItemTags(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagLookup<Block>>,
    fileHelper: ExistingFileHelper
) : ItemTagsProvider(output, registries, blockTags, EllsSO.MOD_ID, fileHelper) {
    override fun addTags(registries: HolderLookup.Provider) {
        tag(Tags.Items.BARRELS_WOODEN)
            .add(ModItems.BARREL)

        tag(Tags.Items.CHESTS_WOODEN)
            .add(ModItems.CLASSIC_CHEST)
            .add(*ModItems.CHESTS)

        tag(ModTags.SHULKER_BOXES)
            .add(*ModItems.SHULKER_BOXES)

        for ((color, item) in ModItems.DYED_SHULKER_BOXES) {
            when (color) {
                DyeColor.WHITE -> tag(Tags.Items.DYED_WHITE).add(item)
                DyeColor.ORANGE -> tag(Tags.Items.DYED_ORANGE).add(item)
                DyeColor.MAGENTA -> tag(Tags.Items.DYED_MAGENTA).add(item)
                DyeColor.LIGHT_BLUE -> tag(Tags.Items.DYED_LIGHT_BLUE).add(item)
                DyeColor.YELLOW -> tag(Tags.Items.DYED_YELLOW).add(item)
                DyeColor.LIME -> tag(Tags.Items.DYED_LIME).add(item)
                DyeColor.PINK -> tag(Tags.Items.DYED_PINK).add(item)
                DyeColor.GRAY -> tag(Tags.Items.DYED_GRAY).add(item)
                DyeColor.LIGHT_GRAY -> tag(Tags.Items.DYED_LIGHT_GRAY).add(item)
                DyeColor.CYAN -> tag(Tags.Items.DYED_CYAN).add(item)
                DyeColor.PURPLE -> tag(Tags.Items.DYED_PURPLE).add(item)
                DyeColor.BLUE -> tag(Tags.Items.DYED_BLUE).add(item)
                DyeColor.BROWN -> tag(Tags.Items.DYED_BROWN).add(item)
                DyeColor.GREEN -> tag(Tags.Items.DYED_GREEN).add(item)
                DyeColor.RED -> tag(Tags.Items.DYED_RED).add(item)
                DyeColor.BLACK -> tag(Tags.Items.DYED_BLACK).add(item)
            }
        }

        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
            .add(ModItems.MINI_CHEST)
    }

    override fun getName() = "Item Tags"
}