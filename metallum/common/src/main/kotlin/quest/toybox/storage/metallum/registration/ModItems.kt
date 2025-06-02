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

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }

    fun registerCreativeTab(builder: CreativeModeTab.Builder) {
        val tab = builder
            .icon { COPPER_BARREL.defaultInstance }
            .title(Component.translatable("tab.storagemetallum.main"))
            .displayItems { params, output ->
                output.accept(COPPER_BARREL)
            }
            .build()

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EllsSM.id("main"), tab)
    }
}