package quest.toybox.storage.metallum.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.registration.ModBlocks

class BlockLootProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {
        for (block in ModBlocks.BARRELS) {
            add(block, ::createNameableBlockEntityTable)
        }

        for(block in ModBlocks.SHULKER_BOXES) {
            add(block, ::createNameableBlockEntityTable)
        }

        for(block in ModBlocks.CHESTS) {
            add(block, ::createNameableBlockEntityTable)
        }
    }

    override fun getKnownBlocks(): Iterable<Block?> {
        return BuiltInRegistries.BLOCK.entrySet()
            .filter { it.key.location().namespace == EllsSM.MOD_ID }
            .map { it.value }
            .toSet()
    }
}