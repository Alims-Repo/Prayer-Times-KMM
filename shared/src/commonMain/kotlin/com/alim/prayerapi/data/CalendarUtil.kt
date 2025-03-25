/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.data

import com.alim.prayerapi.model.Rounding
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * A utility object for handling date/time calculations,
 * including rounding, manipulation, and conversions in UTC.
 */
object CalendarUtil {

  /**
   * Determines whether a given year is a leap year (366 days).
   *
   * @param year The year to check.
   * @return `true` if the year is a leap year; otherwise `false`.
   */
  fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)
  }

  /**
   * Rounds the seconds of a [LocalDateTime] to the nearest, upper, or no rounding at all.
   *
   * @param localDateTime The original date and time.
   * @param rounding The rounding strategy to apply to seconds.
   * @return A new [LocalDateTime] with the seconds adjusted to zero and the minutes updated if rounding applies.
   */
  fun roundedMinute(
    localDateTime: LocalDateTime,
    rounding: Rounding = Rounding.NEAREST
  ): LocalDateTime {
    val originalMinute = localDateTime.minute
    val (minute, second) = when (rounding) {
      Rounding.NEAREST -> originalMinute + (localDateTime.second / 60f).roundToInt() to 0
      Rounding.UP -> originalMinute + ceil(localDateTime.second / 60f).roundToInt() to 0
      Rounding.NONE -> originalMinute to localDateTime.second
    }

    val localDateTimeWithOldMinutes = LocalDateTime(
      year = localDateTime.year,
      monthNumber = localDateTime.monthNumber,
      dayOfMonth = localDateTime.dayOfMonth,
      hour = localDateTime.hour,
      minute = originalMinute,
      second = second
    )

    return if (originalMinute != minute) {
      val delta = minute - originalMinute
      add(localDateTimeWithOldMinutes, delta, DateTimeUnit.MINUTE)
    } else {
      localDateTimeWithOldMinutes
    }
  }

  /**
   * Resolves a [LocalDateTime] from [DateComponents] with time set to 00:00:00 UTC.
   *
   * @param components The year, month, and day components.
   * @return A [LocalDateTime] at midnight UTC for the specified date.
   */
  fun resolveTime(components: DateComponents): LocalDateTime {
    return resolveTime(components.year, components.month, components.day)
  }

  /**
   * Adds a specific amount of time to a [LocalDateTime].
   *
   * @param localDateTime The original date and time.
   * @param amount The amount to add.
   * @param dateTimeUnit The unit of time to add.
   * @return A new [LocalDateTime] with the added time.
   */
  fun add(localDateTime: LocalDateTime, amount: Int, dateTimeUnit: DateTimeUnit): LocalDateTime {
    val timezone = TimeZone.UTC
    val instant = localDateTime.toInstant(timezone)
    return add(instant, amount, dateTimeUnit)
  }

  /**
   * Adds a specific amount of time to an [Instant].
   *
   * @param instant The instant to add to.
   * @param amount The amount of time to add.
   * @param dateTimeUnit The unit of time to add.
   * @return A new [LocalDateTime] representing the result in UTC.
   */
  fun add(instant: Instant, amount: Int, dateTimeUnit: DateTimeUnit): LocalDateTime {
    val timezone = TimeZone.UTC
    val updatedInstant = instant.plus(amount, dateTimeUnit, timezone)
    return updatedInstant.toLocalDateTime(timezone)
  }

  /**
   * Builds a [LocalDateTime] at 00:00:00 UTC for a specific date.
   *
   * @param year The year.
   * @param month The month number (1–12).
   * @param day The day of the month.
   * @return A [LocalDateTime] at the beginning of the day in UTC.
   */
  private fun resolveTime(year: Int, month: Int, day: Int): LocalDateTime {
    return LocalDateTime(year, month, day, 0, 0, 0)
  }

  /**
   * Extension function to convert a [LocalDateTime] to [Instant] using UTC.
   *
   * @return An [Instant] representing the UTC version of this date-time.
   */
  fun LocalDateTime.toUtcInstant(): Instant = toInstant(TimeZone.UTC)
}
