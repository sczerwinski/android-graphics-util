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

package it.czerwinski.android.graphics.matchers

import android.graphics.RectF
import org.mockito.ArgumentMatcher
import java.io.Serializable

class RectFMatcher(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val delta: Float
) : ArgumentMatcher<RectF>, Serializable {

    override fun matches(argument: RectF?): Boolean =
        argument != null
                && left == argument.left
                && top == argument.top
                && right == argument.right
                && bottom == argument.bottom

    override fun toString(): String =
        "eq(RectF($left, $top, $right, $bottom), $delta)"

}
