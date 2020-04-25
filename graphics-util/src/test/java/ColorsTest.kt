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

import android.graphics.Color
import androidx.annotation.ColorInt
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
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

    @Test
    fun `Given hue, saturation and value, when hsvColor, then return a color`() {
        val hue = 180.0f
        val saturation = 0.5f
        val value = 0.75f
        @ColorInt val expectedColor = 0xFF60C0C0.toInt()

        mockkStatic(Color::class)
        every { Color.HSVToColor(any()) } returns expectedColor

        @ColorInt val result = hsvColor(hue = hue, saturation = saturation, value = value)

        assertEquals(expectedColor, result)

        verify(exactly = 1) { Color.HSVToColor(floatArrayOf(180.0f, 0.5f, 0.75f)) }
    }

    @Test
    fun `Given a color, when colorHue, then return color hue`() {
        @ColorInt val color = 0xFF60C0C0.toInt()
        val hsvSlot = slot<FloatArray>()

        mockkStatic(Color::class)
        every { Color.colorToHSV(any(), capture(hsvSlot)) } answers {
            hsvSlot.captured[0] = 180.0f
            hsvSlot.captured[1] = 0.5f
            hsvSlot.captured[2] = 0.75f
        }

        @ColorInt val result = color.colorHue()

        assertEquals(180.0f, result)

        verify(exactly = 1) { Color.colorToHSV(color, refEq(hsvSlot.captured)) }
    }

    @Test
    fun `Given a color, when colorSaturation, then return color saturation`() {
        @ColorInt val color = 0xFF60C0C0.toInt()
        val hsvSlot = slot<FloatArray>()

        mockkStatic(Color::class)
        every { Color.colorToHSV(any(), capture(hsvSlot)) } answers {
            hsvSlot.captured[0] = 180.0f
            hsvSlot.captured[1] = 0.5f
            hsvSlot.captured[2] = 0.75f
        }

        @ColorInt val result = color.colorSaturation()

        assertEquals(0.5f, result)

        verify(exactly = 1) { Color.colorToHSV(color, refEq(hsvSlot.captured)) }
    }

    @Test
    fun `Given a color, when colorValue, then return color value`() {
        @ColorInt val color = 0xFF60C0C0.toInt()
        val hsvSlot = slot<FloatArray>()

        mockkStatic(Color::class)
        every { Color.colorToHSV(any(), capture(hsvSlot)) } answers {
            hsvSlot.captured[0] = 180.0f
            hsvSlot.captured[1] = 0.5f
            hsvSlot.captured[2] = 0.75f
        }

        @ColorInt val result = color.colorValue()

        assertEquals(0.75f, result)

        verify(exactly = 1) { Color.colorToHSV(color, refEq(hsvSlot.captured)) }
    }
}
