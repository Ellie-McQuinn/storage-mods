package quest.toybox.storage.metallum.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.registration.ModBlocks
import java.util.concurrent.CompletableFuture

class CommonBlockTags(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    fileHelper: ExistingFileHelper
) : BlockTagsProvider(output, registries, EllsSM.MOD_ID, fileHelper) {
    override fun addTags(registries: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlocks.COPPER_BARREL)

        tag(BlockTags.GUARDED_BY_PIGLINS)
            .add(ModBlocks.COPPER_BARREL)
    }

    override fun getName() = "Block Tags"
}

class CommonItemTags(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagLookup<Block>>,
    fileHelper: ExistingFileHelper
) : ItemTagsProvider(output, registries, blockTags, EllsSM.MOD_ID, fileHelper) {
    override fun addTags(registries: HolderLookup.Provider) {

    }

    override fun getName() = "Item Tags"
}