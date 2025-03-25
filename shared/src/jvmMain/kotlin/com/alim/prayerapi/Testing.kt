package com.alim.prayerapi

import com.alim.prayerapi.data.DateComponents
import kotlinx.datetime.Clock.System
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.time.ExperimentalTime

fun main() {
    val coordinates = Coordinates(22.3593445,91.8220829)
    val dateComponents: DateComponents = DateComponents.from(System.now())
    val parameters: CalculationParameters = CalculationMethod.KARACHI.parameters.copy(
        madhab = Madhab.HANAFI
    )

    val prayerTimes = PrayerTimes(coordinates, dateComponents, parameters)

    val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a")
    formatter.timeZone = TimeZone.getTimeZone("Asia/Dhaka")

    println("Fajr: " + formatter.format(prayerTimes.fajr.asDate()))
    println("Sunrise: " + formatter.format(prayerTimes.sunrise.asDate()))
    println("Dhuhr: " + formatter.format(prayerTimes.dhuhr.asDate()))
    println("Asr: " + formatter.format(prayerTimes.asr.asDate()))
    println("Maghrib: " + formatter.format(prayerTimes.maghrib.asDate()))
    println("Isha: " + formatter.format(prayerTimes.isha.asDate()))
}

@OptIn(ExperimentalTime::class)
fun Instant.asDate() = Date(toEpochMilliseconds())