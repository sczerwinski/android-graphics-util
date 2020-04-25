package it.czerwinski.android.graphics.mockk

import io.mockk.MockKMatcherScope

/**
 * Matches a range from `value - delta` to `value + delta`.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun MockKMatcherScope.eq(value: Float, delta: Float) =
    range(from = value - delta, to = value + delta)
