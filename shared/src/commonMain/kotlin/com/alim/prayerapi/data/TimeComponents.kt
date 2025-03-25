/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.data

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlin.math.floor

/**
 * Represents a time-only structure (hours, minutes, seconds).
 * Use this to apply time to a specific [DateComponents] and generate a full [LocalDateTime].
 *
 * @property hours The hour component (0–23).
 * @property minutes The minute component (0–59).
 * @property seconds The second component (0–59).
 */
class TimeComponents private constructor(
  val hours: Int,
  val minutes: Int,
  val seconds: Int
) {

  /**
   * Combines this time with a given [DateComponents] to return a complete [LocalDateTime].
   *
   * @param date The date to apply this time to.
   * @return A [LocalDateTime] representing the full date and time.
   */
  fun dateComponents(date: DateComponents): LocalDateTime {
    val base = LocalDateTime(date.year, date.month, date.day, 0, minutes, seconds)
    return CalendarUtil.add(base, hours, DateTimeUnit.HOUR)
  }

  companion object {
    /**
     * Creates a [TimeComponents] instance from a decimal hour value.
     *
     * Example: `18.75` becomes 18h 45m 0s.
     *
     * @param value The hour value as a Double.
     * @return A [TimeComponents] instance, or `null` if the input is invalid (NaN or Infinity).
     */
    fun fromDouble(value: Double): TimeComponents? {
      if (value.isInfinite() || value.isNaN()) return null

      val hours = floor(value)
      val minutes = floor((value - hours) * 60.0)
      val seconds = floor((value - (hours + minutes / 60.0)) * 3600)

      return TimeComponents(
        hours.toInt(),
        minutes.toInt(),
        seconds.toInt()
      )
    }
  }
}
