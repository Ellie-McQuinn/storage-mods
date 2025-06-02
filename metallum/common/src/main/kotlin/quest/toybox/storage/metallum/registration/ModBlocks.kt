package quest.toybox.storage.metallum.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour.Properties as BlockProperties
import quest.toybox.storage.metallum.EllsSM
import quest.toybox.storage.metallum.block.TallBarrelBlock

object ModBlocks {
    val COPPER_BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        EllsSM.id("copper_barrel"),
        TallBarrelBlock(BlockProperties.ofFullCopy(Blocks.BARREL))
    )

    fun init(alias: (ResourceLocation, ResourceLocation) -> Unit) {

    }
}