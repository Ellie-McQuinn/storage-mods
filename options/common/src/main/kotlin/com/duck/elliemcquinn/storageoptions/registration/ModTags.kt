package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.EllsSO
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object ModTags {
    @JvmField
    val SHULKER_BOXES: TagKey<Item> = TagKey.create(Registries.ITEM, EllsSO.id("shulker_boxes"))
}