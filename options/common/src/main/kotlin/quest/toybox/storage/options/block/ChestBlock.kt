package quest.toybox.storage.options.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.AChestBlock
import quest.toybox.storage.options.registration.ModBlockEntities

class ChestBlock(properties: Properties) : AChestBlock(properties) {
    override fun blockEntityType() = ModBlockEntities.CHEST

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<ChestBlock> = simpleCodec(::ChestBlock)
    }
}