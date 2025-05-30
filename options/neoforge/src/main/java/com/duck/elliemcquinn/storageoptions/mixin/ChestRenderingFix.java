package com.duck.elliemcquinn.storageoptions.mixin;

import com.duck.elliemcquinn.storageoptions.block.ClassicChestBlock;
import com.duck.elliemcquinn.storageoptions.block.entity.ChestBlockEntity;
import com.duck.elliemcquinn.storageoptions.client.ChestBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.IBlockEntityRendererExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChestBlockRenderer.class)
public abstract class ChestRenderingFix implements IBlockEntityRendererExtension<ChestBlockEntity> {
    @NotNull
    @Override
    public AABB getRenderBoundingBox(ChestBlockEntity entity) {
        BlockState state = entity.getBlockState();
        BlockPos pos = entity.getBlockPos();

        Direction connectedDirection = ClassicChestBlock.Companion.getConnectedDirection(state);

        if (connectedDirection == null) {
            return AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(pos));
        } else {
            return AABB.encapsulatingFullBlocks(pos, pos.relative(connectedDirection));
        }
    }
}
