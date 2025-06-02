package quest.toybox.storage.metallum.registration

import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import quest.toybox.storage.library.block.entity.TallBarrelBlockEntity
import quest.toybox.storage.metallum.EllsSM

object ModBlockEntities {
    val BARREL: BlockEntityType<TallBarrelBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        EllsSM.id("barrel"),
        BlockEntityType.Builder.of(::createBarrelBlockEntity, ModBlocks.COPPER_BARREL).build(null)
    )

    fun createBarrelBlockEntity(pos: BlockPos, state: BlockState): TallBarrelBlockEntity {
        return TallBarrelBlockEntity(BARREL, pos, state)
    }

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }
}