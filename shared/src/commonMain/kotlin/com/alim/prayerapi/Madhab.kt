/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi

import com.alim.prayerapi.internal.ShadowLength
import com.alim.prayerapi.internal.ShadowLength.DOUBLE
import com.alim.prayerapi.internal.ShadowLength.SINGLE

/**
 * Madhab for determining how Asr is calculated
 */
enum class Madhab {
  /**
   * Shafi Madhab
   */
  SHAFI,

  /**
   * Hanafi Madhab
   */
  HANAFI;

  val shadowLength: ShadowLength
    get() = when (this) {
      SHAFI -> SINGLE
      HANAFI -> DOUBLE
    }
}