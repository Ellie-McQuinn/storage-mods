package quest.toybox.storage.options

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import quest.toybox.storage.library.client.ChestBlockItemRenderer
import quest.toybox.storage.library.client.ChestBlockRenderer
import quest.toybox.storage.library.client.ChestModel
import quest.toybox.storage.library.client.MiniChestMenuScreen
import quest.toybox.storage.library.client.ShulkerBoxItemRenderer
import quest.toybox.storage.library.client.ShulkerBoxModel
import quest.toybox.storage.library.client.ShulkerBoxRenderer
import quest.toybox.storage.options.registration.ModBlockEntities
import quest.toybox.storage.options.registration.ModItems
import quest.toybox.storage.options.registration.ModMenus

object FabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.SHULKER_BOX, ::ShulkerBoxRenderer)

        for (item in ModItems.SHULKER_BOXES) {
            BuiltinItemRendererRegistry.INSTANCE.register(item, ShulkerBoxItemRenderer)
        }

        EntityModelLayerRegistry.registerModelLayer(ShulkerBoxModel.MODEL_LAYER, ShulkerBoxModel::createLayer)

        MenuScreens.register(ModMenus.MINI_CHEST, ::MiniChestMenuScreen)

        BlockEntityRenderers.register(ModBlockEntities.CHEST, ::ChestBlockRenderer)

        for (chest in ModItems.CHESTS) {
            BuiltinItemRendererRegistry.INSTANCE.register(chest, ChestBlockItemRenderer)
        }

        EntityModelLayerRegistry.registerModelLayer(ChestModel.SINGLE_MODEL_LAYER, ChestModel::createSingleLayer)
        EntityModelLayerRegistry.registerModelLayer(ChestModel.LEFT_MODEL_LAYER, ChestModel::createLeftLayer)
        EntityModelLayerRegistry.registerModelLayer(ChestModel.RIGHT_MODEL_LAYER, ChestModel::createRightLayer)
    }
}