package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.EllsSO
import com.duck.elliemcquinn.storageoptions.block.entity.ChestBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.ClassicChestBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.DoubleInventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.MiniChestBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.ShulkerBoxBlockEntity
import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

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

    fun init() {
        // NO-OP for now
    }
}