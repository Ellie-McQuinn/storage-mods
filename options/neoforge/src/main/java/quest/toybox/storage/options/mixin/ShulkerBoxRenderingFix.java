package quest.toybox.storage.options.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.extensions.IBlockEntityRendererExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import quest.toybox.storage.options.block.entity.ShulkerBoxBlockEntity;
import quest.toybox.storage.options.client.ShulkerBoxRenderer;

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
