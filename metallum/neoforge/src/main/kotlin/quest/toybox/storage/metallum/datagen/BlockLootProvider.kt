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
        add(ModBlocks.COPPER_BARREL, ::createNameableBlockEntityTable)
    }

    override fun getKnownBlocks(): Iterable<Block?> {
        return BuiltInRegistries.BLOCK.entrySet()
            .filter { it.key.location().namespace == EllsSM.MOD_ID }
            .map { it.value }
            .toSet()
    }
}