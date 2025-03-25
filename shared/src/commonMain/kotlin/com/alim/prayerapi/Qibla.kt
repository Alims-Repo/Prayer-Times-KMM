/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi

import com.alim.prayerapi.Coordinates
import com.alim.prayerapi.internal.QiblaUtil

class Qibla(coordinates: Coordinates) {
  val direction: Double = QiblaUtil.calculateQiblaDirection(coordinates)

  companion object {
    private val MAKKAH = Coordinates(21.4225241, 39.8261818)
  }
}