package it.czerwinski.android.graphics

/**
 * Converts angle measured in radians to degrees.
 *
 * @return Angle measured in degrees.
 */
fun Float.radToDeg(): Float = this * FULL_ANGLE / DOUBLE_PI

/**
 * Converts angle measured in degrees to radians.
 *
 * @return Angle measured in radians.
 */
fun Float.degToRad(): Float = this * DOUBLE_PI / FULL_ANGLE
