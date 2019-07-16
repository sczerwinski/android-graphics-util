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

import android.graphics.RectF
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RectFTest {

    @Mock
    lateinit var rect: RectF

    @Before
    @Throws(Exception::class)
    fun resetMock() {
        rect.left = 0f
        rect.top = 0f
        rect.right = 0f
        rect.bottom = 0f
    }

    @Test
    @Throws(Exception::class)
    fun shouldSetRectCoordinatesFromCircle() {
        rect.setCircle(cx = 1f, cy = 2f, radius = 0.5f)

        assertEquals(0.5f, rect.left, DELTA)
        assertEquals(1.5f, rect.top, DELTA)
        assertEquals(1.5f, rect.right, DELTA)
        assertEquals(2.5f, rect.bottom, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun shouldSetRectCoordinatesFromOval() {
        rect.setOval(cx = 1f, cy = 2f, rx = 0.5f, ry = 0.7f)

        assertEquals(0.5f, rect.left, DELTA)
        assertEquals(1.3f, rect.top, DELTA)
        assertEquals(1.5f, rect.right, DELTA)
        assertEquals(2.7f, rect.bottom, DELTA)
    }
}
