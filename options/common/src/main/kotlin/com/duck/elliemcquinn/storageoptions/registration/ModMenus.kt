package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.EllsSO
import com.duck.elliemcquinn.storageoptions.block.menu.MiniChestMenu
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.MenuType

object ModMenus {
    val MINI_CHEST: MenuType<MiniChestMenu> = Registry.register(
        BuiltInRegistries.MENU,
        EllsSO.id("mini_chest"),
        MenuType(::MiniChestMenu, FeatureFlags.VANILLA_SET)
    )

    fun init() {
        // NO-OP for now.
    }
}