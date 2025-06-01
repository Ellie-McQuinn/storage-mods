package quest.toybox.storage.options.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.ATallBarrelBlock
import quest.toybox.storage.options.registration.ModBlockEntities

class TallBarrelBlock(properties: Properties) : ATallBarrelBlock(properties) {
    override fun blockEntityType() = ModBlockEntities.BARREL

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<TallBarrelBlock> = simpleCodec(::TallBarrelBlock)
    }
}