package quest.toybox.storage.metallum.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour.Properties as BlockProperties
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.block.ChestBlock
import quest.toybox.storage.metallum.block.ShulkerBoxBlock
import quest.toybox.storage.metallum.block.TallBarrelBlock

object ModBlocks {
    val COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val EXPOSED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("exposed_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val WEATHERED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("weathered_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val OXIDIZED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("oxidized_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val WAXED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val WAXED_EXPOSED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_exposed_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val WAXED_WEATHERED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_weathered_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val WAXED_OXIDIZED_COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_oxidized_copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val IRON_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("iron_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val GOLDEN_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("golden_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val DIAMOND_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("diamond_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val NETHERITE_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("netherite_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val BARRELS = arrayOf(
        COPPER_BARREL, EXPOSED_COPPER_BARREL, WEATHERED_COPPER_BARREL, OXIDIZED_COPPER_BARREL,
        WAXED_COPPER_BARREL, WAXED_EXPOSED_COPPER_BARREL, WAXED_WEATHERED_COPPER_BARREL, WAXED_OXIDIZED_COPPER_BARREL,
        IRON_BARREL, GOLDEN_BARREL, DIAMOND_BARREL, NETHERITE_BARREL
    )

    val COPPER_SHULKER_BOX: ShulkerBoxBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("copper_shulker_box"),
        ShulkerBoxBlock(null, BlockProperties.ofFullCopy(Blocks.SHULKER_BOX))
    )

    val SHULKER_BOXES = arrayOf(
        COPPER_SHULKER_BOX
    )

    val COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val EXPOSED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("exposed_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val WEATHERED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("weathered_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val OXIDIZED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("oxidized_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val WAXED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val WAXED_EXPOSED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_exposed_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val WAXED_WEATHERED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_weathered_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val WAXED_OXIDIZED_COPPER_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("waxed_oxidized_copper_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val IRON_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("iron_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val GOLDEN_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("golden_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val DIAMOND_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("diamond_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val NETHERITE_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("netherite_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val CHESTS = arrayOf(
        COPPER_CHEST, EXPOSED_COPPER_CHEST, WEATHERED_COPPER_CHEST, OXIDIZED_COPPER_CHEST,
        WAXED_COPPER_CHEST, WAXED_EXPOSED_COPPER_CHEST, WAXED_WEATHERED_COPPER_CHEST, WAXED_OXIDIZED_COPPER_CHEST,
        IRON_CHEST, GOLDEN_CHEST, DIAMOND_CHEST, NETHERITE_CHEST
    )

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }
}