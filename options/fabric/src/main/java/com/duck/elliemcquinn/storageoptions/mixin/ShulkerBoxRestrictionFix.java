package com.duck.elliemcquinn.storageoptions.mixin;

import com.duck.elliemcquinn.storageoptions.registration.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlockEntity.class)
public class ShulkerBoxRestrictionFix {
    @Inject(
            method = "canPlaceItemThroughFace",
            at = @At("HEAD"),
            cancellable = true
    )
    private void storageoptions_rejectModdedShulkerBoxes(int slot, ItemStack stack, Direction face, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModTags.SHULKER_BOXES)) {
            cir.setReturnValue(false);
        }
    }
}
