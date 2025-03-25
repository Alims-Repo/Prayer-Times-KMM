/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.internal

import com.alim.prayerapi.Coordinates
import com.alim.prayerapi.data.DateComponents
import com.alim.prayerapi.internal.Astronomical.approximateTransit
import com.alim.prayerapi.internal.Astronomical.correctedHourAngle
import com.alim.prayerapi.internal.Astronomical.correctedTransit
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.tan

class SolarTime(date: DateComponents, coordinates: Coordinates) {
  val transit: Double
  val sunrise: Double
  val sunset: Double

  private val observer: Coordinates
  private val solar: SolarCoordinates
  private val prevSolar: SolarCoordinates
  private val nextSolar: SolarCoordinates
  private val approximateTransit: Double

  init {
    val julianDate = CalendricalHelper.julianDay(date.year, date.month, date.day)
    prevSolar = SolarCoordinates(julianDate - 1)
    solar = SolarCoordinates(julianDate)
    nextSolar = SolarCoordinates(julianDate + 1)
    approximateTransit = approximateTransit(
      coordinates.longitude,
      solar.apparentSiderealTime, solar.rightAscension
    )
    val solarAltitude = -50.0 / 60.0
    observer = coordinates
    transit = correctedTransit(
      approximateTransit, coordinates.longitude,
      solar.apparentSiderealTime, solar.rightAscension, prevSolar.rightAscension,
      nextSolar.rightAscension
    )
    sunrise = correctedHourAngle(
      approximateTransit, solarAltitude,
      coordinates, false, solar.apparentSiderealTime, solar.rightAscension,
      prevSolar.rightAscension, nextSolar.rightAscension, solar.declination,
      prevSolar.declination, nextSolar.declination
    )
    sunset = correctedHourAngle(
      approximateTransit, solarAltitude,
      coordinates, true, solar.apparentSiderealTime, solar.rightAscension,
      prevSolar.rightAscension, nextSolar.rightAscension, solar.declination,
      prevSolar.declination, nextSolar.declination
    )
  }

  fun timeForSolarAngle(angle: Double, afterTransit: Boolean): Double {
    return correctedHourAngle(
      approximateTransit, angle, coordinates = observer,
      afterTransit, solar.apparentSiderealTime, solar.rightAscension,
      prevSolar.rightAscension, nextSolar.rightAscension, solar.declination,
      prevSolar.declination, nextSolar.declination
    )
  }

  // hours from transit
  fun afternoon(shadowLength: ShadowLength): Double {
    // TODO (from Swift version) source shadow angle calculation
    val tangent: Double = abs(observer.latitude - solar.declination)
    val inverse: Double =
      shadowLength.shadowLength + tan(tangent.toRadians())
    val angle: Double = atan(1.0 / inverse).toDegrees()
    return timeForSolarAngle(angle, true)
  }
}