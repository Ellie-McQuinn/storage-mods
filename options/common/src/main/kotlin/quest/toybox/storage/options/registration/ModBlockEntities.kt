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
        BlockEntityType.Builder.of(::createBarrelBlockEntity, ModBlocks.BARREL).build(null)
    )

    fun createBarrelBlockEntity(pos: BlockPos, state: BlockState): TallBarrelBlockEntity {
        return TallBarrelBlockEntity(BARREL, pos, state)
    }

    val CLASSIC_CHEST: BlockEntityType<ClassicChestBlockEntity<DoubleInventoryBlockEntity<*>>> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("classic_chest"),
        BlockEntityType.Builder.of(::createClassicChestBlockEntity, ModBlocks.CLASSIC_CHEST).build(null)
    )

    fun createClassicChestBlockEntity(pos: BlockPos, state: BlockState): ClassicChestBlockEntity<DoubleInventoryBlockEntity<*>> {
        return ClassicChestBlockEntity(CLASSIC_CHEST, pos, state)
    }

    val SHULKER_BOX: BlockEntityType<ShulkerBoxBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("shulker_box"),
        BlockEntityType.Builder.of(::createShulkerBoxBlockEntity, *ModBlocks.SHULKER_BOXES).build(null)
    )

    fun createShulkerBoxBlockEntity(pos: BlockPos, state: BlockState): ShulkerBoxBlockEntity {
        return ShulkerBoxBlockEntity(SHULKER_BOX, pos, state)
    }

    val MINI_CHEST: BlockEntityType<MiniChestBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("mini_chest"),
        BlockEntityType.Builder.of(::createMiniChestBlockEntity, ModBlocks.MINI_CHEST).build(null)
    )

    fun createMiniChestBlockEntity(pos: BlockPos, state: BlockState): MiniChestBlockEntity {
        return MiniChestBlockEntity(MINI_CHEST, pos, state)
    }

    val CHEST: BlockEntityType<ChestBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSO.id("chest"),
        BlockEntityType.Builder.of(::createChestBlockEntity, *ModBlocks.CHESTS).build(null)
    )

    fun createChestBlockEntity(pos: BlockPos, state: BlockState): ChestBlockEntity {
        return ChestBlockEntity(CHEST, pos, state)
    }

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {
        // Migrations for pre 0.5.0 content.
        alias(EllsSO.oldId("barrel"), EllsSO.id("barrel"))
        alias(EllsSO.oldId("classic_chest"), EllsSO.id("classic_chest"))
        alias(EllsSO.oldId("shulker_box"), EllsSO.id("shulker_box"))
        alias(EllsSO.oldId("mini_chest"), EllsSO.id("mini_chest"))
        alias(EllsSO.oldId("chest"), EllsSO.id("chest"))
    }
}