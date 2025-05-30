package quest.toybox.storage.options.registration

import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.library.block.entity.ChestBlockEntity
import quest.toybox.storage.library.block.entity.ClassicChestBlockEntity
import quest.toybox.storage.library.block.entity.DoubleInventoryBlockEntity
import quest.toybox.storage.library.block.entity.MiniChestBlockEntity
import quest.toybox.storage.library.block.entity.ShulkerBoxBlockEntity
import quest.toybox.storage.library.block.entity.TallBarrelBlockEntity

object ModBlockEntities {
    val BARREL: BlockEntityType<TallBarrelBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("barrel"),
        BlockEntityType.Builder.of(::TallBarrelBlockEntity, ModBlocks.BARREL).build(null)
    )

    val CLASSIC_CHEST: BlockEntityType<ClassicChestBlockEntity<DoubleInventoryBlockEntity<*>>> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("classic_chest"),
        BlockEntityType.Builder.of(fun (pos: BlockPos, state: BlockState): ClassicChestBlockEntity<*> {
            return ClassicChestBlockEntity<DoubleInventoryBlockEntity<*>>(pos, state)
        }, ModBlocks.CLASSIC_CHEST).build(null)
    )

    val SHULKER_BOX: BlockEntityType<ShulkerBoxBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("shulker_box"),
        BlockEntityType.Builder.of(::ShulkerBoxBlockEntity, *ModBlocks.SHULKER_BOXES).build(null)
    )

    val MINI_CHEST: BlockEntityType<MiniChestBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("mini_chest"),
        BlockEntityType.Builder.of(::MiniChestBlockEntity, ModBlocks.MINI_CHEST).build(null)
    )

    val CHEST: BlockEntityType<ChestBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("chest"),
        BlockEntityType.Builder.of(::ChestBlockEntity, *ModBlocks.CHESTS).build(null)
    )

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {
        // Migrations for pre 0.5.0 content.
        alias(EllsSO.oldId("barrel"), EllsSO.id("barrel"))
        alias(EllsSO.oldId("classic_chest"), EllsSO.id("classic_chest"))
        alias(EllsSO.oldId("shulker_box"), EllsSO.id("shulker_box"))
        alias(EllsSO.oldId("mini_chest"), EllsSO.id("mini_chest"))
        alias(EllsSO.oldId("chest"), EllsSO.id("chest"))
    }
}