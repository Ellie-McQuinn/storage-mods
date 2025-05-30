package quest.toybox.storage.options

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import quest.toybox.storage.library.client.ChestBlockItemExtensions
import quest.toybox.storage.library.client.ChestBlockRenderer
import quest.toybox.storage.library.client.ChestModel
import quest.toybox.storage.library.client.MiniChestMenuScreen
import quest.toybox.storage.library.client.ShulkerBoxItemExtensions
import quest.toybox.storage.library.client.ShulkerBoxModel
import quest.toybox.storage.library.client.ShulkerBoxRenderer
import quest.toybox.storage.options.registration.ModBlockEntities
import quest.toybox.storage.options.registration.ModItems
import quest.toybox.storage.options.registration.ModMenus

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