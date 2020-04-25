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

import androidx.annotation.ColorInt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ColorsTest {

    @Test
    fun `Given two colors, when mixColors, then return a color between both colors`() {
        @ColorInt val color1 = 0x6080a0e0
        @ColorInt val color2 = 0x40302010

        @ColorInt val result = mixColors(color1, color2)

        assertEquals(0x50586078, result)
    }

    @Test
    fun `Given two colors, when mixColors, then return a color at the ratio between both colors`() {
        @ColorInt val color1 = 0x00000000
        @ColorInt val color2 = 0x40808080
        val ratio = .25f

        @ColorInt val result = mixColors(color1, color2, ratio)

        assertEquals(0x10202020, result)
    }
}
