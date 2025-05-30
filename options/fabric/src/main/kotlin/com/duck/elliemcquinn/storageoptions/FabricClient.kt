package com.duck.elliemcquinn.storageoptions

import com.duck.elliemcquinn.storageoptions.client.ChestBlockItemRenderer
import com.duck.elliemcquinn.storageoptions.client.ChestBlockRenderer
import com.duck.elliemcquinn.storageoptions.client.ChestModel
import com.duck.elliemcquinn.storageoptions.client.MiniChestMenuScreen
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxItemRenderer
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxModel
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxRenderer
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import com.duck.elliemcquinn.storageoptions.registration.ModMenus
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers

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