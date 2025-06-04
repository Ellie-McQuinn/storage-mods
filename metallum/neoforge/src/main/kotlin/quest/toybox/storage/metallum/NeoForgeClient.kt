package quest.toybox.storage.metallum

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import quest.toybox.storage.library.client.ChestBlockItemExtensions
import quest.toybox.storage.library.client.ChestBlockRenderer
import quest.toybox.storage.library.client.ShulkerBoxItemExtensions
import quest.toybox.storage.library.client.ShulkerBoxRenderer
import quest.toybox.storage.metallum.registration.ModBlockEntities
import quest.toybox.storage.metallum.registration.ModItems

@Mod(EllsSM.MOD_ID, dist = [Dist.CLIENT])
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
    }
}