/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.internal

import kotlin.math.floor
import kotlin.math.roundToInt

internal object DoubleUtil {
  fun normalizeWithBound(value: Double, max: Double): Double {
    return value - max * floor(value / max)
  }

  fun unwindAngle(value: Double): Double {
    return normalizeWithBound(value, 360.0)
  }

  fun closestAngle(angle: Double): Double {
    return if (angle >= -180 && angle <= 180) {
      angle
    } else angle - 360 * (angle / 360).roundToInt()
  }
}