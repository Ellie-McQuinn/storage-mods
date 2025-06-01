package quest.toybox.storage.options.block

import com.mojang.serialization.MapCodec
import quest.toybox.storage.library.block.AClassicChestBlock
import quest.toybox.storage.library.block.entity.ClassicChestBlockEntity
import quest.toybox.storage.options.registration.ModBlockEntities

class ClassicChestBlock(properties: Properties) : AClassicChestBlock<ClassicChestBlockEntity<*>>(properties = properties) {
    override fun blockEntityType() = ModBlockEntities.CLASSIC_CHEST

    override fun codec()= CODEC

    companion object {
        val CODEC: MapCodec<ClassicChestBlock> = simpleCodec(::ClassicChestBlock)
    }
}