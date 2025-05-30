package quest.toybox.storage.options.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.library.block.ChestBlock
import quest.toybox.storage.library.block.ClassicChestBlock
import quest.toybox.storage.library.block.MiniChestBlock
import quest.toybox.storage.library.block.ShulkerBoxBlock
import quest.toybox.storage.library.block.TallBarrelBlock
import quest.toybox.storage.library.block.entity.ShulkerBoxBlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour.Properties as BlockProperties
import net.minecraft.world.level.block.ShulkerBoxBlock as VanillaShulkerBoxBlock

object ModBlocks {
    val BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    val CLASSIC_CHEST: ClassicChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("classic_chest"),
        ClassicChestBlock(BlockProperties.ofFullCopy(Blocks.OAK_PLANKS))
    )

    val DEFAULT_SHULKER_BOX: ShulkerBoxBlock = shulkerBox("shulker_box", Blocks.SHULKER_BOX)

    val DYED_SHULKER_BOXES: Map<DyeColor, ShulkerBoxBlock> = mapOf(
        DyeColor.WHITE to shulkerBox("white_shulker_box", Blocks.WHITE_SHULKER_BOX),
        DyeColor.ORANGE to shulkerBox("orange_shulker_box", Blocks.ORANGE_SHULKER_BOX),
        DyeColor.MAGENTA to shulkerBox("magenta_shulker_box", Blocks.MAGENTA_SHULKER_BOX),
        DyeColor.LIGHT_BLUE to shulkerBox("light_blue_shulker_box", Blocks.LIGHT_BLUE_SHULKER_BOX),
        DyeColor.YELLOW to shulkerBox("yellow_shulker_box", Blocks.YELLOW_SHULKER_BOX),
        DyeColor.LIME to shulkerBox("lime_shulker_box", Blocks.LIME_SHULKER_BOX),
        DyeColor.PINK to shulkerBox("pink_shulker_box", Blocks.PINK_SHULKER_BOX),
        DyeColor.GRAY to shulkerBox("gray_shulker_box", Blocks.GRAY_SHULKER_BOX),
        DyeColor.LIGHT_GRAY to shulkerBox("light_gray_shulker_box", Blocks.LIGHT_GRAY_SHULKER_BOX),
        DyeColor.CYAN to shulkerBox("cyan_shulker_box", Blocks.CYAN_SHULKER_BOX),
        DyeColor.PURPLE to shulkerBox("purple_shulker_box", Blocks.PURPLE_SHULKER_BOX),
        DyeColor.BLUE to shulkerBox("blue_shulker_box", Blocks.BLUE_SHULKER_BOX),
        DyeColor.BROWN to shulkerBox("brown_shulker_box", Blocks.BROWN_SHULKER_BOX),
        DyeColor.GREEN to shulkerBox("green_shulker_box", Blocks.GREEN_SHULKER_BOX),
        DyeColor.RED to shulkerBox("red_shulker_box", Blocks.RED_SHULKER_BOX),
        DyeColor.BLACK to shulkerBox("black_shulker_box", Blocks.BLACK_SHULKER_BOX)
    )

    val SHULKER_BOXES: Array<ShulkerBoxBlock> = arrayOf(
        DEFAULT_SHULKER_BOX,
        *DYED_SHULKER_BOXES.values.toTypedArray()
    )

    val MINI_CHEST: MiniChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("mini_chest"),
        MiniChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val OAK_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("oak_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val SPRUCE_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("spruce_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val DARK_OAK_CHEST: ChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSO.id("dark_oak_chest"),
        ChestBlock(BlockProperties.ofFullCopy(Blocks.CHEST))
    )

    val CHESTS = arrayOf(OAK_CHEST, SPRUCE_CHEST, DARK_OAK_CHEST)

    fun shulkerBox(name: String, base: Block): ShulkerBoxBlock {
        val base = base as? VanillaShulkerBoxBlock ?: throw IllegalArgumentException("base must be a vanilla shulker box")

        return Registry.register(BuiltInRegistries.BLOCK, EllsSO.id(name),
            ShulkerBoxBlock(base.color, BlockProperties.ofFullCopy(base)
                .isSuffocating(ShulkerBoxBlockEntity::isClosed)
                .isViewBlocking(ShulkerBoxBlockEntity::isClosed)
            ))
    }

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {
        // Migrations for pre 0.5.0 content.
        alias(EllsSO.oldId("barrel"), EllsSO.id("barrel"))
        alias(EllsSO.oldId("classic_chest"), EllsSO.id("classic_chest"))

        for (block in SHULKER_BOXES) {
            alias(EllsSO.oldId(EllsSO.id(block).path), EllsSO.id(block))
        }

        alias(EllsSO.oldId("mini_chest"), EllsSO.id("mini_chest"))
        alias(EllsSO.oldId("oak_chest"), EllsSO.id("oak_chest"))
        alias(EllsSO.oldId("spruce_chest"), EllsSO.id("spruce_chest"))
        alias(EllsSO.oldId("dark_oak_chest"), EllsSO.id("dark_oak_chest"))
    }
}