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
import it.czerwinski.android.graphics.matchers.rectFEq
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.eq
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AdvancedPathTest {

    @Spy
    lateinit var path: AdvancedPath

    @Test
    @Throws(Exception::class)
    fun shouldAppendArcOfCircle() {
        doNothing().`when`(path).arcTo(any(RectF::class.java), anyFloat(), anyFloat(), anyBoolean())

        path.arcTo(
            cx = 1f,
            cy = 2f,
            radius = 0.5f,
            startAngle = 10f,
            sweepAngle = 20f,
            forceMoveTo = true
        )

        inOrder(path).apply {
            verify(path).arcTo(
                rectFEq(0.5f, 1.5f, 1.5f, 2.5f, DELTA),
                eq(10f, DELTA),
                eq(20f, DELTA),
                eq(true)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldAddConvexCircleSector() {
        doNothing().`when`(path).arcTo(
            cx = anyFloat(),
            cy = anyFloat(),
            radius = anyFloat(),
            startAngle = anyFloat(),
            sweepAngle = anyFloat(),
            forceMoveTo = anyBoolean()
        )
        doNothing().`when`(path).lineTo(anyFloat(), anyFloat())
        doNothing().`when`(path).close()

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 90f,
            inset = 0.1f
        )

        inOrder(path).apply {
            verify(path).arcTo(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(10f, DELTA),
                eq(90.57f, BIG_DELTA),
                eq(88.85f, BIG_DELTA),
                eq(true)
            )
            verify(path).lineTo(
                eq(0.9f, DELTA),
                eq(2.1f, DELTA)
            )
            verify(path).close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldAddConcaveCircleSector() {
        doNothing().`when`(path).arcTo(
            cx = anyFloat(),
            cy = anyFloat(),
            radius = anyFloat(),
            startAngle = anyFloat(),
            sweepAngle = anyFloat(),
            forceMoveTo = anyBoolean()
        )
        doNothing().`when`(path).close()

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 270f,
            inset = 0.1f
        )

        inOrder(path).apply {
            verify(path).arcTo(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(10f, DELTA),
                eq(90.57f, BIG_DELTA),
                eq(268.85f, BIG_DELTA),
                eq(true)
            )
            verify(path).arcTo(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(0.1f, DELTA),
                eq(270f, DELTA),
                eq(-90f, DELTA),
                eq(false)
            )
            verify(path).close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldAddFullCircleSector() {
        doNothing().`when`(path).addCircle(anyFloat(), anyFloat(), anyFloat(), any())

        path.addCircleSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 200f,
            sweepAngle = 360f,
            inset = 0.1f
        )

        inOrder(path).apply {
            verify(path).addCircle(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(10f, DELTA),
                eq(Path.Direction.CCW)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldAddRingSector() {
        doNothing().`when`(path).arcTo(
            cx = anyFloat(),
            cy = anyFloat(),
            radius = anyFloat(),
            startAngle = anyFloat(),
            sweepAngle = anyFloat(),
            forceMoveTo = anyBoolean()
        )
        doNothing().`when`(path).close()

        path.addRingSector(
            cx = 1f,
            cy = 2f,
            radius = 10f,
            startAngle = 90f,
            sweepAngle = 90f,
            thickness = 2.5f,
            inset = 0.1f
        )

        inOrder(path).apply {
            verify(path).arcTo(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(10f, DELTA),
                eq(90.57f, BIG_DELTA),
                eq(88.85f, BIG_DELTA),
                eq(true)
            )
            verify(path).arcTo(
                eq(1f, DELTA),
                eq(2f, DELTA),
                eq(7.5f, DELTA),
                eq(179.24f, BIG_DELTA),
                eq(-88.47f, BIG_DELTA),
                eq(false)
            )
            verify(path).close()
        }
    }
}
