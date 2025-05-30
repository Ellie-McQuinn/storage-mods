package quest.toybox.storage.options.registration

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import quest.toybox.storage.options.EllsSO

object ModTags {
    @JvmField
    val SHULKER_BOXES: TagKey<Item> = TagKey.create(Registries.ITEM, EllsSO.id("shulker_boxes"))
}