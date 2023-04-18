package com.lewan.convert_impl.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.animateClick(
    duration: Long = 250L,
    interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
    scaleXY: Float = 0.8f
) {
    val animationSet = AnimatorSet()

    val toScaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, scaleXY)
    toScaleY.repeatMode = ValueAnimator.REVERSE
    toScaleY.repeatCount = 1

    val toScaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, scaleXY)
    toScaleX.repeatMode = ValueAnimator.REVERSE
    toScaleX.repeatCount = 1

    animationSet.duration = duration
    animationSet.interpolator = interpolator
    animationSet.playTogether(toScaleX, toScaleY)
    animationSet.start()
}
