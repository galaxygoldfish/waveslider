/*   Copyright 2023 Sebastian Hriscu
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.galaxygoldfish.waveslider

/**
 * @param reverseDirection Whether to animate the wave in the reverse direction.
 * (By default the wave moves to the right)
 * @param flatlineOnDrag Whether to have the slider become a straight line when dragged
 * and be a wave when released
 * @param animateWave Whether to animate the wave infinitely
 * @param reverseFlatline Have the slider be a flat line by default and show the wave
 * when dragged
 */
data class WaveAnimationOptions(
    var reverseDirection: Boolean = false,
    var flatlineOnDrag: Boolean = true,
    var animateWave: Boolean = true,
    var reverseFlatline: Boolean = false
)
