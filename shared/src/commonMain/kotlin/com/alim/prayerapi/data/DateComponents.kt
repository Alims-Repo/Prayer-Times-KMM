/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A lightweight class representing a date (year, month, and day) without time or timezone.
 *
 * @property year The year component of the date.
 * @property month The month component (1–12).
 * @property day The day of the month.
 */
class DateComponents(val year: Int, val month: Int, val day: Int) {

  companion object {
    /**
     * Creates a [DateComponents] instance from an [Instant], based on the device's system timezone.
     *
     * @param instant The instant in time to convert.
     * @return A [DateComponents] object representing the year, month, and day.
     */
    fun from(instant: Instant): DateComponents {
      val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
      return fromLocalDateTime(localDateTime)
    }

    /**
     * Creates a [DateComponents] instance from a [LocalDateTime].
     *
     * @param date The [LocalDateTime] to convert.
     * @return A [DateComponents] object representing the year, month, and day.
     */
    fun fromLocalDateTime(date: LocalDateTime): DateComponents {
      return DateComponents(date.year, date.monthNumber, date.dayOfMonth)
    }
  }
}
