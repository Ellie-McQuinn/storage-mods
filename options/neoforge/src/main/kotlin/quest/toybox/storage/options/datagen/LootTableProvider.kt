package quest.toybox.storage.options.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.concurrent.CompletableFuture

class LootTableProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : LootTableProvider(
    output, setOf(), listOf(SubProviderEntry(::BlockLootProvider, LootContextParamSets.BLOCK)), registries
)