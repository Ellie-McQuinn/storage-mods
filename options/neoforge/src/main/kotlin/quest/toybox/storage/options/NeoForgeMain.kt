package quest.toybox.storage.options

import net.minecraft.core.Direction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import net.neoforged.neoforge.items.IItemHandlerModifiable
import net.neoforged.neoforge.registries.RegisterEvent
import quest.toybox.storage.options.block.entity.InventoryBlockEntity
import quest.toybox.storage.options.registration.ModBlockEntities
import quest.toybox.storage.options.registration.ModBlocks
import quest.toybox.storage.options.registration.ModItems
import quest.toybox.storage.options.registration.ModMenus
import quest.toybox.storage.options.registration.ModRecipes

@Mod(EllsSO.MOD_ID)
class NeoForgeMain(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.addListener(RegisterEvent::class.java) { event ->
            when (event.registryKey) {
                Registries.BLOCK -> ModBlocks.init(BuiltInRegistries.BLOCK::addAlias)
                Registries.BLOCK_ENTITY_TYPE -> ModBlockEntities.init(BuiltInRegistries.BLOCK_ENTITY_TYPE::addAlias)
                Registries.ITEM -> ModItems.init(BuiltInRegistries.ITEM::addAlias)
                Registries.CREATIVE_MODE_TAB -> ModItems.registerCreativeTab(CreativeModeTab.builder())
                Registries.RECIPE_SERIALIZER -> ModRecipes.init()
                Registries.MENU -> ModMenus.init()
            }
        }

        modBus.addListener(RegisterCapabilitiesEvent::class.java) { event ->
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.BARREL, ::getItemHandler)
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.CLASSIC_CHEST, ::getItemHandler)
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.SHULKER_BOX, ::getItemHandler)
        }
    }

    companion object {
        fun getItemHandler(entity: InventoryBlockEntity, context: Direction): IItemHandlerModifiable {
            return entity.getItemAccess() as IItemHandlerModifiable
        }
    }
}