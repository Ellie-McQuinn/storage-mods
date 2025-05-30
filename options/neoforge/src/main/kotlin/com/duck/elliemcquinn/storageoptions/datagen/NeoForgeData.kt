package com.duck.elliemcquinn.storageoptions.datagen

import com.duck.elliemcquinn.storageoptions.EllsSO
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = EllsSO.MOD_ID)
object NeoForgeData {
    @JvmStatic
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generateCommonData = System.getProperty("storageoptions.datagen.common", "false") == "true"

        val generator = event.generator
        val output = generator.packOutput
        val registries = event.lookupProvider
        val fileHelper = event.existingFileHelper

        if (generateCommonData) {
            generator.addProvider(event.includeServer(), LootTableProvider(output, registries))

            val blockTags: BlockTagsProvider = CommonBlockTags(output, registries, fileHelper)
            generator.addProvider(event.includeServer(), blockTags)
            generator.addProvider(event.includeServer(), CommonItemTags(output, registries, blockTags.contentsGetter(), fileHelper))
            generator.addProvider(event.includeServer(), RecipeProvider(output, registries))

            generator.addProvider(event.includeClient(), BlockStateProvider(output, fileHelper))
        } else {

        }
    }
}