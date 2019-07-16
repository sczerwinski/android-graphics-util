/*
 * Copyright 2019 Slawomir Czerwinski
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

@file:JvmName(name = "Angles")

package it.czerwinski.android.graphics

/**
 * Converts angle measured in radians to degrees.
 *
 * @return Angle measured in degrees.
 */
fun Float.radToDeg(): Float = this * FULL_ANGLE / DOUBLE_PI

/**
 * Converts angle measured in degrees to radians.
 *
 * @return Angle measured in radians.
 */
fun Float.degToRad(): Float = this * DOUBLE_PI / FULL_ANGLE

/**
 * Calculates angle measured in degrees for the length of the arc and the specified [radius].
 *
 * @param radius The radius of the arc.
 * @return The calculated angle measured in degrees.
 */
fun Float.arcLengthToAngle(radius: Float): Float = (this / radius).radToDeg()

/**
 * Calculates length of the arc with the angle measured in degrees and the specified [radius].
 *
 * @param radius The radius of the arc.
 * @return The calculated length of the arc.
 */
fun Float.angleToArcLength(radius: Float): Float = degToRad() * radius
