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

@file:JvmName(name = "MockKMatchers")

package it.czerwinski.android.graphics.mockk

import io.mockk.MockKMatcherScope

/**
 * Matches a range from `value - delta` to `value + delta`.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun MockKMatcherScope.eq(value: Float, delta: Float) =
    range(from = value - delta, to = value + delta)
