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

@file:JvmName(name = "Canvas")

package it.czerwinski.android.graphics

import android.graphics.Canvas
import androidx.core.graphics.withTranslation
import kotlin.math.cos
import kotlin.math.sin

/**
 * Wraps the specified [block] in a call to `Canvas.withTranslation()`
 * with the specified radial coordinates.
 *
 * @param distance Translation distance.
 * @param angle Translation angle in degrees.
 * @param block A block of instructions to be executed with the specified translation.
 */
inline fun Canvas.withRadialTranslation(
    distance: Float,
    angle: Float,
    block: Canvas.() -> Unit
) = withTranslation(
    x = distance * cos(angle.degToRad()),
    y = distance * sin(angle.degToRad()),
    block = block
)
