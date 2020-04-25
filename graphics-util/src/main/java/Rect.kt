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

@file:JvmName(name = "Rect")

package it.czerwinski.android.graphics

import android.graphics.Rect
import android.graphics.RectF
import kotlin.math.roundToInt

/**
 * Copies the coordinates from `src` rounded to integer into this rectangle.
 *
 * @receiver The rectangle.
 * @param src The rectangle whose coordinates are copied into this rectangle.
 */
fun Rect.set(src: RectF) {
    this.left = src.left.roundToInt()
    this.top = src.top.roundToInt()
    this.right = src.right.roundToInt()
    this.bottom = src.bottom.roundToInt()
}

/**
 * Copies the coordinates from the circle defined by its center and radius.
 *
 * @receiver The rectangle.
 * @param cx The x-coordinate of the center of the circle.
 * @param cy The x-coordinate of the center of the circle.
 * @param radius The radius of the circle.
 */
fun Rect.setCircle(cx: Int, cy: Int, radius: Int) {
    this.left = cx - radius
    this.top = cy - radius
    this.right = cx + radius
    this.bottom = cy + radius
}

/**
 * Copies the coordinates from the oval defined by its center and radii.
 *
 * @receiver The rectangle.
 * @param cx The x-coordinate of the center of the oval.
 * @param cy The x-coordinate of the center of the oval.
 * @param rx The x-radius of the oval.
 * @param ry The y-radius of the oval.
 */
fun Rect.setOval(cx: Int, cy: Int, rx: Int, ry: Int) {
    this.left = cx - rx
    this.top = cy - ry
    this.right = cx + rx
    this.bottom = cy + ry
}
