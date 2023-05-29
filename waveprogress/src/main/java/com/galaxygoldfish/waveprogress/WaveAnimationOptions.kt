package com.galaxygoldfish.waveprogress

/**
 * @param reverseDirection Whether to animate the wave in the reverse direction.
 * (By default the wave moves to the right)
 * @param flatlineOnDrag Whether to have the slider become a straight line when dragged
 * and be a wave when released
 * @param animateWave Whether to animate the wave infinitely
 * @param reverseFlatline  Have the slider be a flat line by default and show the wave
 * when dragged
 */
data class WaveAnimationOptions(
    var reverseDirection: Boolean = false,
    var flatlineOnDrag: Boolean = true,
    var animateWave: Boolean = true,
    var reverseFlatline: Boolean = false
)
