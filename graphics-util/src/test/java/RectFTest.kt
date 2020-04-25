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
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RectFTest {

    @SpyK(name = "rectF")
    private var rect = RectF()

    @BeforeEach
    fun resetMock() {
        rect.left = 0f
        rect.top = 0f
        rect.right = 0f
        rect.bottom = 0f
    }

    @Test
    fun `Given circle center and radius, when setCircle, then set rectangle to circumscribe the circle`() {
        rect.setCircle(cx = 1f, cy = 2f, radius = 0.5f)

        assertEquals(0.5f, rect.left, DELTA)
        assertEquals(1.5f, rect.top, DELTA)
        assertEquals(1.5f, rect.right, DELTA)
        assertEquals(2.5f, rect.bottom, DELTA)
    }

    @Test
    fun `Given oval center and radii, when setOval, then set rectangle to circumscribe the oval`() {
        rect.setOval(cx = 1f, cy = 2f, rx = 0.5f, ry = 0.7f)

        assertEquals(0.5f, rect.left, DELTA)
        assertEquals(1.3f, rect.top, DELTA)
        assertEquals(1.5f, rect.right, DELTA)
        assertEquals(2.7f, rect.bottom, DELTA)
    }
}
