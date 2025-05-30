package com.duck.elliemcquinn.storageoptions

import com.duck.elliemcquinn.storageoptions.block.entity.InventoryBlockEntity
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModBlocks
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import com.duck.elliemcquinn.storageoptions.registration.ModMenus
import com.duck.elliemcquinn.storageoptions.registration.ModRecipes
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage

object FabricMain : ModInitializer {
    override fun onInitialize() {
        ModBlocks.init()
        ModBlockEntities.init()
        ModItems.init()
        ModItems.registerCreativeTab(FabricItemGroup.builder())
        ModRecipes.init()
        ModMenus.init()

        ItemStorage.SIDED.registerForBlockEntities({ entity, direction ->
            @Suppress("UNCHECKED_CAST")
            (entity as InventoryBlockEntity).getItemAccess() as Storage<ItemVariant>
        }, ModBlockEntities.BARREL, ModBlockEntities.CLASSIC_CHEST, ModBlockEntities.SHULKER_BOX)
    }
}