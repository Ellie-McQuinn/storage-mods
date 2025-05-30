package quest.toybox.storage.options

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import quest.toybox.storage.options.block.entity.InventoryBlockEntity
import quest.toybox.storage.options.registration.ModBlockEntities
import quest.toybox.storage.options.registration.ModBlocks
import quest.toybox.storage.options.registration.ModItems
import quest.toybox.storage.options.registration.ModMenus
import quest.toybox.storage.options.registration.ModRecipes

object FabricMain : ModInitializer {
    override fun onInitialize() {
        ModBlocks.init({ old, new -> Unit })
        ModBlockEntities.init({ old, new -> Unit })
        ModItems.init({ old, new -> Unit })
        ModItems.registerCreativeTab(FabricItemGroup.builder())
        ModRecipes.init()
        ModMenus.init()

        ItemStorage.SIDED.registerForBlockEntities({ entity, direction ->
            @Suppress("UNCHECKED_CAST")
            (entity as InventoryBlockEntity).getItemAccess() as Storage<ItemVariant>
        }, ModBlockEntities.BARREL, ModBlockEntities.CLASSIC_CHEST, ModBlockEntities.SHULKER_BOX)
    }
}