package com.duck.elliemcquinn.storageoptions

import com.duck.elliemcquinn.storageoptions.client.ChestBlockItemExtensions
import com.duck.elliemcquinn.storageoptions.client.ChestBlockRenderer
import com.duck.elliemcquinn.storageoptions.client.ChestModel
import com.duck.elliemcquinn.storageoptions.client.MiniChestMenuScreen
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxItemExtensions
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxModel
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxRenderer
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import com.duck.elliemcquinn.storageoptions.registration.ModMenus
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent

@Mod(EllsSO.MOD_ID, dist = [Dist.CLIENT])
class NeoForgeClient(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.addListener<EntityRenderersEvent.RegisterRenderers> { event ->
            event.registerBlockEntityRenderer(ModBlockEntities.SHULKER_BOX, ::ShulkerBoxRenderer)
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST, ::ChestBlockRenderer)
        }

        modBus.addListener<RegisterClientExtensionsEvent> { event ->
            event.registerItem(ShulkerBoxItemExtensions, *ModItems.SHULKER_BOXES)
            event.registerItem(ChestBlockItemExtensions, *ModItems.CHESTS)
        }

        modBus.addListener<EntityRenderersEvent.RegisterLayerDefinitions> { event ->
            event.registerLayerDefinition(ShulkerBoxModel.MODEL_LAYER, ShulkerBoxModel::createLayer)

            event.registerLayerDefinition(ChestModel.SINGLE_MODEL_LAYER, ChestModel::createSingleLayer)
            event.registerLayerDefinition(ChestModel.LEFT_MODEL_LAYER, ChestModel::createLeftLayer)
            event.registerLayerDefinition(ChestModel.RIGHT_MODEL_LAYER, ChestModel::createRightLayer)
        }

        modBus.addListener<RegisterMenuScreensEvent> { event ->
            event.register(ModMenus.MINI_CHEST, ::MiniChestMenuScreen)
        }
    }
}