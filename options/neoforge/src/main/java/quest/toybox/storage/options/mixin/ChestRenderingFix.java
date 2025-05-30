package quest.toybox.storage.options.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.IBlockEntityRendererExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import quest.toybox.storage.options.block.ClassicChestBlock;
import quest.toybox.storage.options.block.entity.ChestBlockEntity;
import quest.toybox.storage.options.client.ChestBlockRenderer;

@Mixin(ChestBlockRenderer.class)
public abstract class ChestRenderingFix implements IBlockEntityRendererExtension<ChestBlockEntity> {
    @NotNull
    @Override
    public AABB getRenderBoundingBox(ChestBlockEntity entity) {
        BlockState state = entity.getBlockState();
        BlockPos pos = entity.getBlockPos();

        Direction connectedDirection = ClassicChestBlock.getConnectedDirection(state);

        if (connectedDirection == null) {
            return AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(pos));
        } else {
            return AABB.encapsulatingFullBlocks(pos, pos.relative(connectedDirection));
        }
    }
}
