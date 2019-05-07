package it.czerwinski.android.graphics

import org.junit.Assert.assertEquals
import org.junit.Test

class AnglesTest {

    @Test
    @Throws(Exception::class)
    fun radiansShouldBeConvertedToDegrees() {
        val radAngle = PI / 2f

        val result = radAngle.radToDeg()

        assertEquals(RIGHT_ANGLE, result, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun degreesShouldBeConvertedToRadians() {
        val degAngle = STRAIGHT_ANGLE

        val result = degAngle.degToRad()

        assertEquals(PI, result, DELTA)
    }
}
