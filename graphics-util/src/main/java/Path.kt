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

@file:JvmName(name = "Path")

package it.czerwinski.android.graphics

import android.graphics.Path

/**
 * Clear any lines and curves from this path, making it empty,
 * and applies a new path defined in `init` function.
 *
 * **Example:**
 * ```kotlin
 * path.set(close = true) {
 *     moveTo(0f, 0f)
 *     lineTo(10f, 0f)
 *     lineTo(10f, 10f)
 *     lineTo(0f, 10f)
 * }
 * ```
 *
 * @receiver The path.
 * @param close Set to `true` if the path should be closed upon completion.
 * @param init Function initializing a new path.
 */
inline fun <T : Path> T.set(close: Boolean = false, init: T.() -> Unit) {
    reset()
    init()
    if (close) close()
}
