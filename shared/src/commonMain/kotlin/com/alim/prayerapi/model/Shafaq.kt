/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.model

/**
 * Shafaq is the twilight in the sky. Different madhabs define the appearance of
 * twilight differently. These values are used by the MoonsightingComittee method
 * for the different ways to calculate Isha.
 */
enum class Shafaq {
  // General is a combination of Ahmer and Abyad.
  GENERAL,

  // Ahmar means the twilight is the red glow in the sky. Used by the Shafi, Maliki, and Hanbali madhabs.
  AHMER,

  // Abyad means the twilight is the white glow in the sky. Used by the Hanafi madhab.
  ABYAD
}