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

import android.graphics.Canvas
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CanvasTest {

    @MockK
    lateinit var canvas: Canvas

    @Test
    fun `Given a canvas, when withRadialTranslation, then draw translated by correct coordinates`() {
        every { canvas.save() } returns 1
        every { canvas.translate(any(), any()) } just Runs
        every { canvas.saveCount } returns 1
        every { canvas.restoreToCount(any()) } just Runs

        canvas.withRadialTranslation(
            distance = 10f,
            angle = 30f
        ) {
            assertEquals(1, saveCount)
        }

        val xSlot = slot<Float>()
        val ySlot = slot<Float>()

        verifyOrder {
            canvas.save()
            canvas.translate(capture(xSlot), capture(ySlot))
            canvas.saveCount
            canvas.restoreToCount(eq(value = 1))
        }
        confirmVerified(canvas)

        assertEquals(8.660254f, xSlot.captured, DELTA)
        assertEquals(5f, ySlot.captured, DELTA)
    }
}
