package quest.toybox.storage.metallum.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.AChestBlock
import quest.toybox.storage.metallum.registration.ModBlockEntities

class ChestBlock(properties: Properties) : AChestBlock(properties) {
    override fun blockEntityType() = ModBlockEntities.CHEST

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<ChestBlock> = simpleCodec(::ChestBlock)
    }
}