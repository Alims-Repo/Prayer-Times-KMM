/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi

/**
 * Adjustment value for prayer times, in minutes
 * These values are added (or subtracted) from the prayer time that is calculated before
 * returning the result times.
 */
data class PrayerAdjustments(
  val fajr: Int = 0,
  val sunrise: Int = 0,
  val dhuhr: Int = 0,
  val asr: Int = 0,
  val maghrib: Int = 0,
  val isha: Int = 0
)