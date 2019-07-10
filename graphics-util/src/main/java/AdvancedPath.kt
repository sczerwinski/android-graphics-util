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

package it.czerwinski.android.graphics

import android.graphics.Path
import android.graphics.RectF
import kotlin.math.cos
import kotlin.math.sin

/**
 * An extension of the `Path` class, providing means of building advanced shapes.
 */
open class AdvancedPath : Path() {

    private val rect = RectF()

    /**
     * Appends the specified arc of a circle to the path as a new contour.
     *
     * If the start of the path is different from the path's current last point,
     * an automatic `lineTo()` is added to connect the current contour to the
     * start of the arc.
     *
     * If the path is empty, a `moveTo()` is added with the first point of the arc.
     *
     * @param cx The x-coordinate of the center of the circle.
     * @param cy The x-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param startAngle Starting angle of the arc measured in degrees.
     * @param sweepAngle Sweep angle of the arc measured in degrees.
     * @param forceMoveTo Set to `true`, if a `moveTo()` should always be added before the arc,
     *                    instead of a `lineTo()`.
     */
    open fun arcTo(
        cx: Float = 0f,
        cy: Float = 0f,
        radius: Float,
        startAngle: Float,
        sweepAngle: Float,
        forceMoveTo: Boolean = false
    ) {
        arcTo(
            rect.apply { setCircle(cx, cy, radius) },
            startAngle,
            sweepAngle,
            forceMoveTo
        )
    }

    /**
     * Adds a closed circle sector contour to the path.
     *
     * @param cx The x-coordinate of the center of the circle.
     * @param cy The x-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param startAngle Starting angle of the arc measured in degrees.
     * @param sweepAngle Sweep angle of the arc measured in degrees.
     * @param inset Inset measured from radii enclosing the circle sector.
     */
    open fun addCircleSector(
        cx: Float = 0f,
        cy: Float = 0f,
        radius: Float,
        startAngle: Float,
        sweepAngle: Float,
        inset: Float = 0f
    ) {
        if (sweepAngle >= FULL_ANGLE) {
            addCircle(cx, cy, radius, Direction.CCW)
            return
        }

        val startAngleRad = startAngle.degToRad()
        val sweepAngleRad = sweepAngle.degToRad()

        val angularInset = inset.arcLengthToAngle(radius)

        if (sweepAngle < 2f * angularInset) return

        val centerInset = inset / sin(sweepAngleRad / 2f)

        arcTo(
            cx = cx,
            cy = cy,
            radius = radius,
            startAngle = startAngle + angularInset,
            sweepAngle = sweepAngle - 2f * angularInset,
            forceMoveTo = true
        )

        if (sweepAngle > STRAIGHT_ANGLE && inset > 0f) {
            arcTo(
                cx = cx,
                cy = cy,
                radius = inset,
                startAngle = startAngle + sweepAngle - RIGHT_ANGLE,
                sweepAngle = STRAIGHT_ANGLE - sweepAngle
            )
        } else {
            lineTo(
                cx + centerInset * cos(startAngleRad + sweepAngleRad / 2f),
                cy + centerInset * sin(startAngleRad + sweepAngleRad / 2f)
            )
        }

        close()
    }

    /**
     * Adds a closed ring sector (annulus sector) contour to the path.
     *
     * @param cx The x-coordinate of the center of the circle.
     * @param cy The x-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param startAngle Starting angle of the arc measured in degrees.
     * @param sweepAngle Sweep angle of the arc measured in degrees.
     * @param thickness Thickness of the ring.
     * @param inset Inset measured from radii enclosing the ring sector.
     */
    open fun addRingSector(
        cx: Float = 0f,
        cy: Float = 0f,
        radius: Float,
        startAngle: Float,
        sweepAngle: Float,
        thickness: Float,
        inset: Float = 0f
    ) {
        val outerAngularInset = inset.arcLengthToAngle(radius)
        val innerAngularInset = inset.arcLengthToAngle(radius - thickness)

        arcTo(
            cx = cx,
            cy = cy,
            radius = radius,
            startAngle = startAngle + outerAngularInset,
            sweepAngle = sweepAngle - 2f * outerAngularInset,
            forceMoveTo = true
        )

        arcTo(
            cx = cx,
            cy = cy,
            radius = radius - thickness,
            startAngle = startAngle + sweepAngle - innerAngularInset,
            sweepAngle = 2f * innerAngularInset - sweepAngle
        )

        close()
    }
}
