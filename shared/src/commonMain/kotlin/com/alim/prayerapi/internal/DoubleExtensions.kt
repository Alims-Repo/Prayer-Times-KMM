/*
 * Copyright (c) 2025 Alim Sourav
 * All rights reserved.
 *
 * This file is part of the PrayerTimesAPI project.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 */

package com.alim.prayerapi.internal

import kotlin.math.PI

fun Double.toRadians() = this * PI / 180
fun Double.toDegrees() = (this * 180) / PI
