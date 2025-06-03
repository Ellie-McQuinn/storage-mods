package quest.toybox.storage.metallum.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import quest.toybox.storage.metallum.EllsSM

object ModItems {
    val COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("copper_barrel"),
        BlockItem(ModBlocks.COPPER_BARREL, Item.Properties())
    )

    val IRON_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("iron_barrel"),
        BlockItem(ModBlocks.IRON_BARREL, Item.Properties())
    )

    val GOLDEN_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("golden_barrel"),
        BlockItem(ModBlocks.GOLDEN_BARREL, Item.Properties())
    )

    val DIAMOND_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("diamond_barrel"),
        BlockItem(ModBlocks.DIAMOND_BARREL, Item.Properties())
    )

    val NETHERITE_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("netherite_barrel"),
        BlockItem(ModBlocks.NETHERITE_BARREL, Item.Properties())
    )

    val BARRELS = listOf(COPPER_BARREL, IRON_BARREL, GOLDEN_BARREL, DIAMOND_BARREL, NETHERITE_BARREL)

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }

    fun registerCreativeTab(builder: CreativeModeTab.Builder) {
        val tab = builder
            .icon { COPPER_BARREL.defaultInstance }
            .title(Component.translatable("tab.storagemetallum.main"))
            .displayItems { params, output ->
                output.acceptAll(BARRELS.map { it.defaultInstance })
            }
            .build()

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EllsSM.id("main"), tab)
    }
}