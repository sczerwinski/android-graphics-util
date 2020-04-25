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
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PathTest {

    @MockK
    lateinit var path: Path

    @Test
    fun `When set, then replace existing path`() {
        every { path.reset() } just Runs
        every { path.moveTo(any(), any()) } just Runs
        every { path.lineTo(any(), any()) } just Runs

        path.set {
            moveTo(1f, 1.5f)
            lineTo(2.5f, 3f)
        }

        verifyOrder {
            path.reset()
            path.moveTo(eq(value = 1f), eq(value = 1.5f))
            path.lineTo(eq(value = 2.5f), eq(value = 3f))
        }
        confirmVerified(path)
    }

    @Test
    fun `When set, then replace existing path with a closed path`() {
        every { path.reset() } just Runs
        every { path.moveTo(any(), any()) } just Runs
        every { path.lineTo(any(), any()) } just Runs
        every { path.close() } just Runs

        path.set(close = true) {
            moveTo(1f, 1.5f)
            lineTo(2.5f, 3f)
        }

        verifyOrder {
            path.reset()
            path.moveTo(eq(value = 1f), eq(value = 1.5f))
            path.lineTo(eq(value = 2.5f), eq(value = 3f))
            path.close()
        }
        confirmVerified(path)
    }
}
