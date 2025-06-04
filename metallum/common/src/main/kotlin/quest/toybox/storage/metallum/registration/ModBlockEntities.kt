package quest.toybox.storage.metallum.registration

import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.library.block.entity.ChestBlockEntity
import quest.toybox.storage.library.block.entity.ShulkerBoxBlockEntity
import quest.toybox.storage.library.block.entity.TallBarrelBlockEntity
import quest.toybox.storage.metallum.EllsSM

object ModBlockEntities {
    val BARREL: BlockEntityType<TallBarrelBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSM.id("barrel"),
        BlockEntityType.Builder.of(::createBarrelBlockEntity, *ModBlocks.BARRELS).build(null)
    )

    fun createBarrelBlockEntity(pos: BlockPos, state: BlockState): TallBarrelBlockEntity {
        return TallBarrelBlockEntity(BARREL, pos, state)
    }

    val SHULKER_BOX: BlockEntityType<ShulkerBoxBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSM.id("shulker_box"),
        BlockEntityType.Builder.of(::createShulkerBoxBlockEntity, *ModBlocks.SHULKER_BOXES).build(null)
    )

    fun createShulkerBoxBlockEntity(pos: BlockPos, state: BlockState): ShulkerBoxBlockEntity {
        return ShulkerBoxBlockEntity(SHULKER_BOX, pos, state)
    }

    val CHEST: BlockEntityType<ChestBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSM.id("chest"),
        BlockEntityType.Builder.of(::createChestBlockEntity, *ModBlocks.CHESTS).build(null)
    )

    fun createChestBlockEntity(pos: BlockPos, state: BlockState): ChestBlockEntity {
        return ChestBlockEntity(CHEST, pos, state)
    }

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }
}