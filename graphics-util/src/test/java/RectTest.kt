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

import android.graphics.Rect
import android.graphics.RectF
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RectTest {

    @SpyK(name = "rect")
    private var rect = Rect()

    @BeforeEach
    fun resetMock() {
        rect.left = 0
        rect.top = 0
        rect.right = 0
        rect.bottom = 0
    }

    @Test
    fun `Given a RectF, when set, then set rectangle to match rounded coordinates of RectF`() {
        val source = spyk(RectF(), name = "rectF")
        source.left = 1.1f
        source.top = 2.2f
        source.right = 3.8f
        source.bottom = 4.9f

        rect.set(source)

        assertEquals(1, rect.left)
        assertEquals(2, rect.top)
        assertEquals(4, rect.right)
        assertEquals(5, rect.bottom)
    }

    @Test
    fun `Given circle center and radius, when setCircle, then set rectangle to circumscribe the circle`() {
        rect.setCircle(cx = 10, cy = 20, radius = 5)

        assertEquals(5, rect.left)
        assertEquals(15, rect.top)
        assertEquals(15, rect.right)
        assertEquals(25, rect.bottom)
    }

    @Test
    fun `Given oval center and radii, when setOval, then set rectangle to circumscribe the oval`() {
        rect.setOval(cx = 10, cy = 20, rx = 5, ry = 7)

        assertEquals(5, rect.left)
        assertEquals(13, rect.top)
        assertEquals(15, rect.right)
        assertEquals(27, rect.bottom)
    }
}
