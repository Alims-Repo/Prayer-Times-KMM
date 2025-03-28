/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi

import com.alim.prayerapi.PrayerTimes
import com.alim.prayerapi.data.CalendarUtil
import com.alim.prayerapi.data.CalendarUtil.add
import com.alim.prayerapi.data.CalendarUtil.roundedMinute
import com.alim.prayerapi.data.CalendarUtil.toUtcInstant
import com.alim.prayerapi.data.DateComponents
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant

class SunnahTimes(prayerTimes: PrayerTimes) {
  /* The midpoint between Maghrib and Fajr */
  val middleOfTheNight: Instant

  /* The beginning of the last third of the period between Maghrib and Fajr,
     a recommended time to perform Qiyam */
  val lastThirdOfTheNight: Instant

  init {
    val currentPrayerTimesDate = CalendarUtil.resolveTime(prayerTimes.dateComponents)
    val tomorrowPrayerTimesDate = add(currentPrayerTimesDate, 1, DateTimeUnit.DAY)
    val tomorrowPrayerTimes = prayerTimes.copy(dateComponents = DateComponents.fromLocalDateTime(tomorrowPrayerTimesDate))

    val nightDurationInSeconds =
      (tomorrowPrayerTimes.fajr.toEpochMilliseconds() -
          prayerTimes.maghrib.toEpochMilliseconds()) / 1000
    middleOfTheNight = roundedMinute(
      add(prayerTimes.maghrib, (nightDurationInSeconds / 2.0).toInt(), DateTimeUnit.SECOND)
    ).toUtcInstant()
    lastThirdOfTheNight = roundedMinute(
      add(
        prayerTimes.maghrib,
        (nightDurationInSeconds * (2.0 / 3.0)).toInt(),
        DateTimeUnit.SECOND
      )
    ).toUtcInstant()
  }
}
