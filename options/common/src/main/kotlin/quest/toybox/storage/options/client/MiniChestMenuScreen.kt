package quest.toybox.storage.options.client

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import quest.toybox.storage.options.EllsSO
import quest.toybox.storage.options.block.menu.MiniChestMenu

class MiniChestMenuScreen(menu: MiniChestMenu, playerInventory: Inventory, title: Component) : AbstractContainerScreen<MiniChestMenu>(menu, playerInventory, title) {
    override fun render(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(graphics, mouseX, mouseY, partialTick)
        this.renderTooltip(graphics, mouseX, mouseY)
    }

    override fun renderBg(graphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        val left = (width - imageWidth) / 2
        val top = (height - imageHeight) / 2

        graphics.blit(TEXTURE, left, top, 0, 0, imageWidth, imageHeight)
    }

    companion object {
        val TEXTURE: ResourceLocation = EllsSO.id("textures/gui/container/mini_chest.png")
    }
}