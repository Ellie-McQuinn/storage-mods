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

    val EXPOSED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("exposed_copper_barrel"),
        BlockItem(ModBlocks.EXPOSED_COPPER_BARREL, Item.Properties())
    )

    val WEATHERED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("weathered_copper_barrel"),
        BlockItem(ModBlocks.WEATHERED_COPPER_BARREL, Item.Properties())
    )

    val OXIDIZED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("oxidized_copper_barrel"),
        BlockItem(ModBlocks.OXIDIZED_COPPER_BARREL, Item.Properties())
    )

    val WAXED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_copper_barrel"),
        BlockItem(ModBlocks.WAXED_COPPER_BARREL, Item.Properties())
    )

    val WAXED_EXPOSED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_exposed_copper_barrel"),
        BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_BARREL, Item.Properties())
    )

    val WAXED_WEATHERED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_weathered_copper_barrel"),
        BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_BARREL, Item.Properties())
    )

    val WAXED_OXIDIZED_COPPER_BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_oxidized_copper_barrel"),
        BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL, Item.Properties())
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

    val BARRELS = arrayOf(
        COPPER_BARREL, EXPOSED_COPPER_BARREL, WEATHERED_COPPER_BARREL, OXIDIZED_COPPER_BARREL,
        WAXED_COPPER_BARREL, WAXED_EXPOSED_COPPER_BARREL, WAXED_WEATHERED_COPPER_BARREL, WAXED_OXIDIZED_COPPER_BARREL,
        IRON_BARREL, GOLDEN_BARREL, DIAMOND_BARREL, NETHERITE_BARREL
    )

    val COPPER_SHULKER_BOX: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("copper_shulker_box"),
        BlockItem(ModBlocks.COPPER_SHULKER_BOX, Item.Properties())
    )

    val SHULKER_BOXES = arrayOf(
        COPPER_SHULKER_BOX
    )

    val COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("copper_chest"),
        BlockItem(ModBlocks.COPPER_CHEST, Item.Properties())
    )

    val EXPOSED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("exposed_copper_chest"),
        BlockItem(ModBlocks.EXPOSED_COPPER_CHEST, Item.Properties())
    )

    val WEATHERED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("weathered_copper_chest"),
        BlockItem(ModBlocks.WEATHERED_COPPER_CHEST, Item.Properties())
    )

    val OXIDIZED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("oxidized_copper_chest"),
        BlockItem(ModBlocks.OXIDIZED_COPPER_CHEST, Item.Properties())
    )

    val WAXED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_copper_chest"),
        BlockItem(ModBlocks.WAXED_COPPER_CHEST, Item.Properties())
    )

    val WAXED_EXPOSED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_exposed_copper_chest"),
        BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_CHEST, Item.Properties())
    )

    val WAXED_WEATHERED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_weathered_copper_chest"),
        BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_CHEST, Item.Properties())
    )

    val WAXED_OXIDIZED_COPPER_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_oxidized_copper_chest"),
        BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_CHEST, Item.Properties())
    )

    val TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("trial_chest"),
        BlockItem(ModBlocks.TRIAL_CHEST, Item.Properties())
    )

    val EXPOSED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("exposed_trial_chest"),
        BlockItem(ModBlocks.EXPOSED_TRIAL_CHEST, Item.Properties())
    )

    val WEATHERED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("weathered_trial_chest"),
        BlockItem(ModBlocks.WEATHERED_TRIAL_CHEST, Item.Properties())
    )

    val OXIDIZED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("oxidized_trial_chest"),
        BlockItem(ModBlocks.OXIDIZED_TRIAL_CHEST, Item.Properties())
    )

    val WAXED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_trial_chest"),
        BlockItem(ModBlocks.WAXED_TRIAL_CHEST, Item.Properties())
    )

    val WAXED_EXPOSED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_exposed_trial_chest"),
        BlockItem(ModBlocks.WAXED_EXPOSED_TRIAL_CHEST, Item.Properties())
    )

    val WAXED_WEATHERED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_weathered_trial_chest"),
        BlockItem(ModBlocks.WAXED_WEATHERED_TRIAL_CHEST, Item.Properties())
    )

    val WAXED_OXIDIZED_TRIAL_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("waxed_oxidized_trial_chest"),
        BlockItem(ModBlocks.WAXED_OXIDIZED_TRIAL_CHEST, Item.Properties())
    )

    val IRON_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("iron_chest"),
        BlockItem(ModBlocks.IRON_CHEST, Item.Properties())
    )

    val GOLDEN_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("golden_chest"),
        BlockItem(ModBlocks.GOLDEN_CHEST, Item.Properties())
    )

    val DIAMOND_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("diamond_chest"),
        BlockItem(ModBlocks.DIAMOND_CHEST, Item.Properties())
    )

    val NETHERITE_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        EllsSM.id("netherite_chest"),
        BlockItem(ModBlocks.NETHERITE_CHEST, Item.Properties())
    )

    val CHESTS = arrayOf(
        COPPER_CHEST, EXPOSED_COPPER_CHEST, WEATHERED_COPPER_CHEST, OXIDIZED_COPPER_CHEST,
        WAXED_COPPER_CHEST, WAXED_EXPOSED_COPPER_CHEST, WAXED_WEATHERED_COPPER_CHEST, WAXED_OXIDIZED_COPPER_CHEST,
        TRIAL_CHEST, EXPOSED_TRIAL_CHEST, WEATHERED_TRIAL_CHEST, OXIDIZED_TRIAL_CHEST,
        WAXED_TRIAL_CHEST, WAXED_EXPOSED_TRIAL_CHEST, WAXED_WEATHERED_TRIAL_CHEST, WAXED_OXIDIZED_TRIAL_CHEST,
        IRON_CHEST, GOLDEN_CHEST, DIAMOND_CHEST, NETHERITE_CHEST
    )

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }

    fun registerCreativeTab(builder: CreativeModeTab.Builder) {
        val tab = builder
            .icon { DIAMOND_CHEST.defaultInstance }
            .title(Component.translatable("tab.storagemetallum.main"))
            .displayItems { params, output ->
                output.acceptAll(BARRELS.map { it.defaultInstance })
                output.acceptAll(SHULKER_BOXES.map { it.defaultInstance })
                output.acceptAll(CHESTS.map { it.defaultInstance })
            }
            .build()

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EllsSM.id("main"), tab)
    }
}