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

import android.graphics.Path
import android.graphics.RectF
import io.mockk.*
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import it.czerwinski.android.graphics.mockk.eq
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AdvancedPathTest {

    @SpyK(name = "path")
    private var path = AdvancedPath()

    @Test
    fun `When arcTo, then call add arc to the path`() {
        every { path.arcTo(any(), any(), any(), any()) } just Runs

        path.arcTo(
            cx = 1f,
            cy = 2f,
            radius = 0.5f,
            startAngle = 10f,
            sweepAngle = 20f,
            forceMoveTo = true
        )

        val rectFSlot = slot<RectF>()
        verify(exactly = 1) {
            path.arcTo(
                capture(rectFSlot),
                eq(value = 10f, delta = DELTA),
                eq(value = 20f, delta = DELTA),
                eq(value = true)
            )
        }

        assertEquals(0.5f, rectFSlot.captured.left, DELTA)
        assertEquals(1.5f, rectFSlot.captured.top, DELTA)
        assertEquals(1.5f, rectFSlot.captured.right, DELTA)
        assertEquals(2.5f, rectFSlot.captured.bottom, DELTA)
    }

    @Test
    fun `When addCircleSector with oblique angle, then add convex circle sector to the path`() {
        every {
            path.arcTo(
                cx = any(),
                cy = any(),
                radius = any(),
                startAngle = any(),
                sweepAngle = any(),
                forceMoveTo = any()
            )
        } just Runs
        every { path.lineTo(any(), any()) } just Runs
        every { path.close() } just Runs

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 90f,
            inset = 0.1f
        )

        verifyOrder {
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                eq(value = 90.57f, delta = BIG_DELTA),
                eq(value = 88.85f, delta = BIG_DELTA),
                eq(value = true)
            )
            path.lineTo(
                eq(value = 0.9f, delta = DELTA),
                eq(value = 2.1f, delta = DELTA)
            )
            path.close()
        }
    }

    @Test
    fun `When addCircleSector with reflex angle, then add concave circle sector to the path`() {
        every {
            path.arcTo(
                cx = any(),
                cy = any(),
                radius = any(),
                startAngle = any(),
                sweepAngle = any(),
                forceMoveTo = any()
            )
        } just Runs
        every { path.close() } just Runs

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 270f,
            inset = 0.1f
        )

        verifyOrder {
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                eq(value = 90.57f, delta = BIG_DELTA),
                eq(value = 268.85f, delta = BIG_DELTA),
                eq(value = true)
            )
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 0.1f, delta = DELTA),
                eq(value = 270f, delta = DELTA),
                eq(value = -90f, delta = DELTA),
                eq(value = false)
            )
            path.close()
        }
    }

    @Test
    fun `When addCircleSector with full angle, then add a circle to the path`() {
        every { path.addCircle(any(), any(), any(), any()) } just Runs

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 200f,
            sweepAngle = 360f,
            inset = 0.1f
        )

        verify(exactly = 1) {
            path.addCircle(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                Path.Direction.CCW
            )
        }
    }

    @Test
    fun `When addRingSector, then add a ring sector to the path`() {
        every {
            path.arcTo(
                cx = any(),
                cy = any(),
                radius = any(),
                startAngle = any(),
                sweepAngle = any(),
                forceMoveTo = any()
            )
        } just Runs
        every { path.close() } just Runs

        path.addRingSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 90f,
            thickness = 2.5f,
            inset = 0.1f
        )

        verifyOrder {
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                eq(value = 90.57f, delta = BIG_DELTA),
                eq(value = 88.85f, delta = BIG_DELTA),
                eq(value = true)
            )
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 7.5f, delta = DELTA),
                eq(value = 179.24f, delta = BIG_DELTA),
                eq(value = -88.47f, delta = BIG_DELTA),
                eq(value = false)
            )
            path.close()
        }
    }

    @Test
    fun `When set, then replace existing path`() {
        every { path.reset() } just Runs
        every {
            path.arcTo(
                cx = any(),
                cy = any(),
                radius = any(),
                startAngle = any(),
                sweepAngle = any(),
                forceMoveTo = any()
            )
        } just Runs

        path.set {
            arcTo(
                cx = 1f,
                cy = 2f,
                radius = 0.5f,
                startAngle = 10f,
                sweepAngle = 20f,
                forceMoveTo = true
            )
        }

        verifyOrder {
            path.reset()
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 0.5f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                eq(value = 20f, delta = DELTA),
                eq(value = true)
            )
        }
        confirmVerified(path)
    }

    @Test
    fun `When set, then replace existing path with a closed path`() {
        every { path.reset() } just Runs
        every {
            path.arcTo(
                cx = any(),
                cy = any(),
                radius = any(),
                startAngle = any(),
                sweepAngle = any(),
                forceMoveTo = any()
            )
        } just Runs
        every { path.close() } just Runs

        path.set(close = true) {
            arcTo(
                cx = 1f,
                cy = 2f,
                radius = 0.5f,
                startAngle = 10f,
                sweepAngle = 20f,
                forceMoveTo = true
            )
        }

        verifyOrder {
            path.reset()
            path.arcTo(
                eq(value = 1f, delta = DELTA),
                eq(value = 2f, delta = DELTA),
                eq(value = 0.5f, delta = DELTA),
                eq(value = 10f, delta = DELTA),
                eq(value = 20f, delta = DELTA),
                eq(value = true)
            )
            path.close()
        }
        confirmVerified(path)
    }
}
