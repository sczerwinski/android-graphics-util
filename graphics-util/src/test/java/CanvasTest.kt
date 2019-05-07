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
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CanvasTest {

    @Mock
    lateinit var canvas: Canvas

    @Captor
    lateinit var xCaptor: ArgumentCaptor<Float>

    @Captor
    lateinit var yCaptor: ArgumentCaptor<Float>

    @Captor
    lateinit var countCaptor: ArgumentCaptor<Int>

    @Test
    @Throws(Exception::class)
    fun radiansShouldBeConvertedToDegrees() {
        `when`(canvas.save()).thenReturn(1)
        doNothing().`when`(canvas).translate(anyFloat(), anyFloat())
        `when`(canvas.saveCount).thenReturn(1)
        doNothing().`when`(canvas).restoreToCount(anyInt())

        canvas.withRadialTranslation(
            distance = 10f,
            angle = 30f
        ) {
            assertEquals(1, saveCount)
        }

        inOrder(canvas).apply {
            verify(canvas).save()
            verify(canvas).translate(xCaptor.capture(), yCaptor.capture())
            verify(canvas).saveCount
            verify(canvas).restoreToCount(countCaptor.capture())
            verifyNoMoreInteractions()
        }

        assertEquals(8.660254f, xCaptor.value, DELTA)
        assertEquals(5f, yCaptor.value, DELTA)
        assertEquals(1, countCaptor.value)
    }
}
