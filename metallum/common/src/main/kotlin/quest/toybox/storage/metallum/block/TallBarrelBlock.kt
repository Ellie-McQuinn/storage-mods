package quest.toybox.storage.metallum.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.ATallBarrelBlock
import quest.toybox.storage.metallum.registration.ModBlockEntities

class TallBarrelBlock(properties: Properties) : ATallBarrelBlock(properties) {
    override fun blockEntityType() = ModBlockEntities.BARREL

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<TallBarrelBlock> = simpleCodec(::TallBarrelBlock)
    }
}