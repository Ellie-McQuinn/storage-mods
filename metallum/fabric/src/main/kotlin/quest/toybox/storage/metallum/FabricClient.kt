package quest.toybox.storage.metallum

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import quest.toybox.storage.library.client.ChestBlockItemRenderer
import quest.toybox.storage.library.client.ChestBlockRenderer
import quest.toybox.storage.library.client.ShulkerBoxItemRenderer
import quest.toybox.storage.library.client.ShulkerBoxRenderer
import quest.toybox.storage.metallum.registration.ModBlockEntities
import quest.toybox.storage.metallum.registration.ModItems

object FabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.SHULKER_BOX, ::ShulkerBoxRenderer)

        for (item in ModItems.SHULKER_BOXES) {
            BuiltinItemRendererRegistry.INSTANCE.register(item, ShulkerBoxItemRenderer)
        }

        BlockEntityRenderers.register(ModBlockEntities.CHEST, ::ChestBlockRenderer)

        for (chest in ModItems.CHESTS) {
            BuiltinItemRendererRegistry.INSTANCE.register(chest, ChestBlockItemRenderer)
        }
    }
}