package com.duck.elliemcquinn.storageoptions.block.misc

import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState

open class LidController {
    private var shouldBeOpen: Boolean = false
    protected var openness: Float = 0.0F
    protected var oldOpenness: Float = 0.0F
    private var animation: AnimationState = AnimationState.CLOSED

    open fun onAnimationStateChanged(level: Level, pos: BlockPos, state: BlockState, animation: AnimationState) = Unit

    open fun onLidOpenMore(level: Level, pos: BlockPos, state: BlockState) = Unit

    fun tickLid(level: Level, pos: BlockPos, state: BlockState) {
        oldOpenness = openness

        when (animation) {
            AnimationState.CLOSED -> {
                if (shouldBeOpen) {
                    animation = AnimationState.OPENING
                    onAnimationStateChanged(level, pos, state, animation)
                }
            }
            AnimationState.OPENING -> {
                if (shouldBeOpen) {
                    openness += 0.1F

                    onLidOpenMore(level, pos, state)

                    if (openness >= 1.0F) {
                        openness = 1.0F
                        animation = AnimationState.OPENED
                        onAnimationStateChanged(level, pos, state, animation)
                    }
                } else {
                    animation = AnimationState.CLOSING
                }
            }
            AnimationState.OPENED -> {
                if (!shouldBeOpen) {
                    animation = AnimationState.CLOSING
                    onAnimationStateChanged(level, pos, state, animation)
                }
            }
            AnimationState.CLOSING -> {
                if (shouldBeOpen) {
                    animation = AnimationState.OPENING
                } else {
                    openness -= 0.1F

                    if (openness <= 0.0F) {
                        openness = 0.0F
                        animation = AnimationState.CLOSED
                        onAnimationStateChanged(level, pos, state, animation)
                    }
                }
            }
        }
    }

    fun getOpenNess(partialTicks: Float): Float {
        return Mth.lerp(partialTicks, oldOpenness, openness)
    }

    fun setOpen(shouldBeOpen: Boolean) {
        this.shouldBeOpen = shouldBeOpen
    }

    fun getAnimationState() : AnimationState = animation

    enum class AnimationState {
        CLOSED,
        OPENING,
        OPENED,
        CLOSING
    }
}