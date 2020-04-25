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

package it.czerwinski.android.graphics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnglesTest {

    @Test
    fun `Given an angle in radians, when radToDeg, then return the same angle in degrees`() {
        val radAngle = PI / 2f

        val result = radAngle.radToDeg()

        assertEquals(RIGHT_ANGLE, result, DELTA)
    }

    @Test
    fun `Given an angle in degrees, when degToRad, then return the same angle in radians`() {
        val degAngle = STRAIGHT_ANGLE

        val result = degAngle.degToRad()

        assertEquals(PI, result, DELTA)
    }

    @Test
    fun `Given arc length and radius, when arcLengthToAngle, then return angle of the arc`() {
        val arcLength = PI
        val arcRadius = 2f

        val result = arcLength.arcLengthToAngle(radius = arcRadius)

        assertEquals(RIGHT_ANGLE, result, DELTA)
    }

    @Test
    fun `Given angle and arc radius, when angleToArcLength, then return arc length`() {
        val angle = STRAIGHT_ANGLE
        val arcRadius = 2f

        val result = angle.angleToArcLength(radius = arcRadius)

        assertEquals(DOUBLE_PI, result, DELTA)
    }
}
