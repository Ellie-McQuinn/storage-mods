package quest.toybox.storage.options.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.MenuType
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.options.block.menu.MiniChestMenu

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