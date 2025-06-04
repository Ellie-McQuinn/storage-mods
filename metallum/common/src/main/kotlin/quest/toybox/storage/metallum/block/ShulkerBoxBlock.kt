package quest.toybox.storage.metallum.block

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.item.DyeColor
import quest.toybox.storage.library.block.AShulkerBoxBlock
import quest.toybox.storage.metallum.registration.ModBlockEntities
import java.util.Optional

class ShulkerBoxBlock(color: DyeColor?, properties: Properties) : AShulkerBoxBlock(color, properties) {
    override fun blockEntityType() = ModBlockEntities.SHULKER_BOX

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<ShulkerBoxBlock> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                propertiesCodec(),
                DyeColor.CODEC.optionalFieldOf("color").forGetter { block -> Optional.ofNullable(block.color) },
            ).apply(instance) { properties, color -> ShulkerBoxBlock(color.orElse(null), properties) }
        }
    }
}