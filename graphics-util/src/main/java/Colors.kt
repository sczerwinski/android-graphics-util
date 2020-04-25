/*
 * Copyright 2019-2020 Slawomir Czerwinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName(name = "Colors")

package it.czerwinski.android.graphics

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.core.graphics.component3
import androidx.core.graphics.component4
import kotlin.math.roundToInt

private val hsv = floatArrayOf(0f, 0f, 0f)

/**
 * Returns an `Int` value representing a color being a result of mixing [color1] and [color2]
 * with the specified [ratio].
 *
 * @param color1 `Int` value representing first color.
 * @param color2 `Int` value representing second color.
 * @param ratio Colors mixing ratio.
 *              If this value is equal to 0, the result will be equal to [color1].
 *              If this value is equal to 1, the result will be equal to [color2].
 * @return `Int` value representing mixed color.
 */
@ColorInt
fun mixColors(
    @ColorInt color1: Int,
    @ColorInt color2: Int,
    ratio: Float = .5f
): Int {
    val (a1, r1, g1, b1) = color1
    val (a2, r2, g2, b2) = color2
    return (mixInts(a1, a2, ratio) shl 24) or
            (mixInts(r1, r2, ratio) shl 16) or
            (mixInts(g1, g2, ratio) shl 8) or
            (mixInts(b1, b2, ratio))
}

private fun mixInts(left: Int, right: Int, ratio: Float): Int =
    (left * (1f - ratio) + right * ratio).roundToInt()

/**
 * Creates a color with the given hue, saturation and value.
 *
 * @param hue Color hue.
 * @param saturation Color saturation.
 * @param value Color value.
 * @return `Int` value representing requested color.
 */
@ColorInt
fun hsvColor(
    @FloatRange(from = 0.0, to = 360.0, toInclusive = false) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float
): Int {
    hsv[0] = hue % 360.0f
    hsv[1] = saturation
    hsv[2] = value
    return Color.HSVToColor(hsv)
}

/**
 * Returns hue of a color represented by the given `Int` value.
 *
 * @return Color hue.
 */
fun @receiver:ColorInt Int.colorHue(): Float {
    Color.colorToHSV(this, hsv)
    return hsv[0]
}

/**
 * Returns saturation of a color represented by the given `Int` value.
 *
 * @return Color saturation.
 */
fun @receiver:ColorInt Int.colorSaturation(): Float {
    Color.colorToHSV(this, hsv)
    return hsv[1]
}

/**
 * Returns value of a color represented by the given `Int` value.
 *
 * @return Color value.
 */
fun @receiver:ColorInt Int.colorValue(): Float {
    Color.colorToHSV(this, hsv)
    return hsv[2]
}
