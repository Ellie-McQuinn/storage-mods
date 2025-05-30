package quest.toybox.storage.options.registration

import net.minecraft.core.Registry
import net.minecraft.core.cauldron.CauldronInteraction
import net.minecraft.core.component.DataComponents
import net.minecraft.core.dispenser.ShulkerBoxDispenseBehavior
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.level.block.DispenserBlock
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.options.block.ShulkerBoxBlock
import quest.toybox.storage.options.item.ShulkerBoxBlockItem

object ModItems {
    val BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("barrel"),
        BlockItem(ModBlocks.BARREL, Item.Properties())
    )

    val CLASSIC_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("classic_chest"),
        BlockItem(ModBlocks.CLASSIC_CHEST, Item.Properties())
    )

    val DEFAULT_SHULKER_BOX = shulkerBox(ModBlocks.DEFAULT_SHULKER_BOX)

    val DYED_SHULKER_BOXES = ModBlocks.DYED_SHULKER_BOXES.mapValues { shulkerBox(it.value) }

    val SHULKER_BOXES: Array<ShulkerBoxBlockItem> = arrayOf(
        DEFAULT_SHULKER_BOX,
        *DYED_SHULKER_BOXES.values.toTypedArray()
    )

    val MINI_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("mini_chest"),
        BlockItem(ModBlocks.MINI_CHEST, Item.Properties())
    )

    val OAK_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("oak_chest"),
        BlockItem(ModBlocks.OAK_CHEST, Item.Properties())
    )

    val SPRUCE_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("spruce_chest"),
        BlockItem(ModBlocks.SPRUCE_CHEST, Item.Properties())
    )

    val DARK_OAK_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSO.id("dark_oak_chest"),
        BlockItem(ModBlocks.DARK_OAK_CHEST, Item.Properties())
    )

    val CHESTS = arrayOf(OAK_CHEST, SPRUCE_CHEST, DARK_OAK_CHEST)

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {
        val cauldronInteractions = CauldronInteraction.WATER.map

        for ((_, item) in DYED_SHULKER_BOXES) {
            cauldronInteractions.put(item, ShulkerBoxBlockItem::removeColor)
        }

        for (item in SHULKER_BOXES) {
            DispenserBlock.registerBehavior(item, ShulkerBoxDispenseBehavior())
        }

        // Migrations for pre 0.5.0 content.
        alias(EllsSO.oldId("barrel"), EllsSO.id("barrel"))
        alias(EllsSO.oldId("classic_chest"), EllsSO.id("classic_chest"))

        for (item in SHULKER_BOXES) {
            alias(EllsSO.oldId(item.builtInRegistryHolder().key().location().path), item.builtInRegistryHolder().key().location())
        }

        alias(EllsSO.oldId("mini_chest"), EllsSO.id("mini_chest"))
        alias(EllsSO.oldId("oak_chest"), EllsSO.id("oak_chest"))
        alias(EllsSO.oldId("spruce_chest"), EllsSO.id("spruce_chest"))
        alias(EllsSO.oldId("dark_oak_chest"), EllsSO.id("dark_oak_chest"))
    }

    fun shulkerBox(block: ShulkerBoxBlock): ShulkerBoxBlockItem {
        return Registry.register(
            BuiltInRegistries.ITEM,
            BuiltInRegistries.BLOCK.getKey(block),
            ShulkerBoxBlockItem(block, Item.Properties().stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY))
        )
    }

    fun registerCreativeTab(builder: CreativeModeTab.Builder) {
        val tab = builder
            .icon { CLASSIC_CHEST.defaultInstance }
            .title(Component.translatable("tab.storageoptions.main"))
            .displayItems { params, output ->
                output.accept(BARREL)
                output.accept(CLASSIC_CHEST)
                SHULKER_BOXES.forEach { output.accept(it) }
                output.accept(MINI_CHEST)
                CHESTS.forEach { output.accept(it) }
            }
            .build()

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EllsSO.id("main"), tab)
    }
}