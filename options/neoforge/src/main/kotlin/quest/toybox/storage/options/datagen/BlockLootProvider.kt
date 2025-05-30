package quest.toybox.storage.options.datagen

import com.duck.elliemcquinn.storageoptions.EllsSO
import com.duck.elliemcquinn.storageoptions.registration.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block

class BlockLootProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {
        add(ModBlocks.BARREL, ::createNameableBlockEntityTable)
        add(ModBlocks.CLASSIC_CHEST, ::createNameableBlockEntityTable)
        ModBlocks.SHULKER_BOXES.forEach { add(it, ::createShulkerBoxDrop) }
        add(ModBlocks.MINI_CHEST, ::createNameableBlockEntityTable)
        ModBlocks.CHESTS.forEach { add(it, ::createNameableBlockEntityTable) }
    }

    override fun getKnownBlocks(): Iterable<Block?> {
        return BuiltInRegistries.BLOCK.entrySet()
            .filter { it.key.location().namespace == EllsSO.MOD_ID }
            .map { it.value }
            .toSet()
    }
}