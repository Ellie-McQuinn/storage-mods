package com.duck.elliemcquinn.storageoptions.item

import com.duck.elliemcquinn.storageoptions.block.ShulkerBoxBlock
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUtils
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LayeredCauldronBlock
import net.minecraft.world.level.block.state.BlockState

class ShulkerBoxBlockItem(block: Block, properties: Properties) : BlockItem(block, properties) {
    override fun canFitInsideContainerItems(): Boolean {
        return false
    }

    companion object {
        fun removeColor(state: BlockState, level: Level, pos: BlockPos, player: Player, hand: InteractionHand, stack: ItemStack) : ItemInteractionResult {
            val block = Block.byItem(stack.item)

            if (block !is ShulkerBoxBlock) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
            }

            val newStack = stack.transmuteCopy(ModItems.DEFAULT_SHULKER_BOX, 1)

            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, newStack, false))
            player.awardStat(Stats.CLEAN_SHULKER_BOX)

            LayeredCauldronBlock.lowerFillLevel(state, level, pos)

            return ItemInteractionResult.sidedSuccess(level.isClientSide())
        }
    }
}