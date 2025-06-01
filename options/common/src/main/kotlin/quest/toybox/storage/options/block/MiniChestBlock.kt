package quest.toybox.storage.options.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.AMiniChestBlock
import quest.toybox.storage.options.registration.ModBlockEntities

class MiniChestBlock(properties: Properties) : AMiniChestBlock(properties) {
    override fun blockEntityType() = ModBlockEntities.MINI_CHEST

    override fun codec() = CODEC

    companion object {
        val CODEC: MapCodec<MiniChestBlock> = simpleCodec(::MiniChestBlock)
    }
}