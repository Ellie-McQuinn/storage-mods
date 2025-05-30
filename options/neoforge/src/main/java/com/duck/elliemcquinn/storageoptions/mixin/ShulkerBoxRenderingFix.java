package com.duck.elliemcquinn.storageoptions.mixin;

import com.duck.elliemcquinn.storageoptions.block.entity.ShulkerBoxBlockEntity;
import com.duck.elliemcquinn.storageoptions.client.ShulkerBoxRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.extensions.IBlockEntityRendererExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShulkerBoxRenderer.class)
public abstract class ShulkerBoxRenderingFix implements IBlockEntityRendererExtension<ShulkerBoxBlockEntity> {
    @NotNull
    @Override
    public AABB getRenderBoundingBox(ShulkerBoxBlockEntity entity) {
        BlockState state = entity.getBlockState();
        BlockPos pos = entity.getBlockPos();

        Direction openDirection = state.getValue(BlockStateProperties.FACING);

        return AABB.encapsulatingFullBlocks(pos, pos.relative(openDirection));
    }
}
