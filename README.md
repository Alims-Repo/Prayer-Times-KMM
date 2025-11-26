# Prayer Times KMM

[![Maven Central](https://img.shields.io/maven-central/v/io.github.alims-repo/prayer-times-kmm.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.alims-repo%22%20AND%20a:%22prayer-times-kmm%22)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)

A lightweight, accurate, and highly customizable **Kotlin Multiplatform** library for calculating Islamic prayer times across Android, iOS, JVM, and beyond. Inspired by the robust Adhan algorithm, this library provides precise astronomical calculations for all five daily prayers with support for multiple calculation methods, madhabs, and geographical scenarios.

## 🌟 Overview

Prayer Times KMM is designed for developers building Islamic applications that require reliable prayer time calculations. Whether you're creating a mobile app, desktop application, or server-side service, this library offers:

- **Cross-platform compatibility** - Write once, deploy everywhere with Kotlin Multiplatform
- **Astronomical precision** - Advanced solar time computations with seasonal and latitude adjustments
- **Flexible configuration** - Support for 11+ calculation methods and custom parameters
- **Production-ready** - Handles edge cases like extreme latitudes and twilight periods
- **Developer-friendly** - Clean, intuitive API with comprehensive documentation

Perfect for prayer time apps, Qibla finders, Islamic calendars, mosque management systems, and any application requiring accurate salah timing.

---

## ✨ Key Features

### 🕌 Comprehensive Prayer Calculations
- Calculate all five daily prayers: **Fajr**, **Dhuhr**, **Asr**, **Maghrib**, and **Isha**
- **Sunrise** and **sunset** times with precision
- Current and next prayer detection
- Handles complex geographical scenarios and extreme latitudes

### 🌍 Global Support
- Works with any global location using latitude/longitude coordinates
- Automatic adjustments for seasonal variations
- Special handling for high-latitude regions (midnight sun, polar night)
- Multiple twilight angle configurations

### 🔧 Advanced Customization
- **11+ pre-configured calculation methods** from major Islamic organizations
- **2 Madhab options** (Shafi, Hanafi) for Asr calculation
- Per-prayer time adjustments (add/subtract minutes)
- Method-specific fine-tuning
- Custom Fajr and Isha angles
- Flexible rounding options (nearest, up, down, none)

### 🚀 Kotlin Multiplatform
- **Android** - Full support for mobile apps
- **iOS** - Native iOS integration
- **JVM** - Desktop and server applications
- Consistent API across all platforms
- Type-safe and null-safe

---

## 📦 Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("io.github.alims-repo:prayer-times-kmm:1.0.4-beta")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'io.github.alims-repo:prayer-times-kmm:1.0.4-beta'
}
```

---

## 🚀 Getting Started

### Basic Usage

```kotlin
import io.github.alimsrepo.prayertimes.*

// 1. Define your location coordinates
val coordinates = Coordinates(
    latitude = 21.4225,  // Makkah
    longitude = 39.8262
)

// 2. Specify the date
val dateComponents = DateComponents(
    year = 2024,
    month = 3,
    day = 26
)

// 3. Configure calculation parameters
val calculationParameters = CalculationParameters(
    method = CalculationMethod.UMM_AL_QURA,
    madhab = Madhab.SHAFI,
    rounding = RoundingType.NEAREST
)

// 4. Calculate prayer times
val prayerTimes = PrayerTimes(
    coordinates = coordinates,
    dateComponents = dateComponents,
    calculationParameters = calculationParameters
)

// 5. Access individual prayer times
println("Fajr: ${prayerTimes.fajr}")
println("Sunrise: ${prayerTimes.sunrise}")
println("Dhuhr: ${prayerTimes.dhuhr}")
println("Asr: ${prayerTimes.asr}")
println("Maghrib: ${prayerTimes.maghrib}")
println("Isha: ${prayerTimes.isha}")
```

### Current and Next Prayer

```kotlin
val currentInstant = Clock.System.now()

// Find which prayer time it is now
val currentPrayer = prayerTimes.currentPrayer(currentInstant)
println("Current Prayer: $currentPrayer")

// Find the next upcoming prayer
val nextPrayer = prayerTimes.nextPrayer(currentInstant)
println("Next Prayer: $nextPrayer")

// Get time for a specific prayer
val fajrTime = prayerTimes.timeForPrayer(Prayer.FAJR)
```

---

## 🎨 Advanced Usage

### Custom Prayer Adjustments

Fine-tune prayer times by adding or subtracting minutes:

```kotlin
val customParameters = CalculationParameters(
    method = CalculationMethod.MUSLIM_WORLD_LEAGUE,
    
    // Adjust individual prayer times
    prayerAdjustments = PrayerAdjustments(
        fajr = 2,    // Add 2 minutes to Fajr
        dhuhr = -1,  // Subtract 1 minute from Dhuhr
        asr = 3,     // Add 3 minutes to Asr
        maghrib = 0, // No adjustment
        isha = 0     // No adjustment
    ),
    
    // Method-specific adjustments
    methodAdjustments = MethodAdjustments(
        fajr = 1,   // Additional Fajr adjustment
        isha = -2   // Additional Isha adjustment
    )
)

val adjustedTimes = PrayerTimes(
    coordinates = coordinates,
    dateComponents = dateComponents,
    calculationParameters = customParameters
)
```

### High Latitude Adjustments

For locations with extreme latitudes (e.g., Scandinavia, Alaska):

```kotlin
val highLatitudeParams = CalculationParameters(
    method = CalculationMethod.MUSLIM_WORLD_LEAGUE,
    highLatitudeRule = HighLatitudeRule.MIDDLE_OF_THE_NIGHT, // or SEVENTH_OF_THE_NIGHT, TWILIGHT_ANGLE
    madhab = Madhab.SHAFI
)
```

---

## 📐 Supported Calculation Methods

The library supports calculation methods from major Islamic authorities worldwide:

| Method | Organization | Region |
|--------|-------------|--------|
| `MUSLIM_WORLD_LEAGUE` | Muslim World League | Global |
| `EGYPTIAN` | Egyptian General Authority of Survey | Egypt, Middle East |
| `UMM_AL_QURA` | Umm Al-Qura University | Saudi Arabia |
| `GULF` | Gulf Region | UAE, Bahrain, Oman |
| `KUWAIT` | Ministry of Awqaf and Islamic Affairs | Kuwait |
| `QATAR` | Ministry of Awqaf and Islamic Affairs | Qatar |
| `SINGAPORE` | Majlis Ugama Islam Singapura | Singapore |
| `MOON_SIGHTING_COMMITTEE` | Moonsighting Committee Worldwide | North America |
| `KARACHI` | University of Islamic Sciences | Pakistan, Bangladesh |
| `TEHRAN` | Institute of Geophysics | Iran |
| `NORTH_AMERICA` | Islamic Society of North America | USA, Canada |

### Custom Calculation Method

Create your own method with custom angles:

```kotlin
val customMethod = CalculationParameters(
    fajrAngle = 18.0,     // Fajr angle in degrees
    ishaAngle = 17.0,     // Isha angle in degrees
    ishaInterval = 0,     // Or use interval in minutes instead
    method = CalculationMethod.OTHER
)
```

---

## 🕌 Madhab Selection

Different Islamic schools of thought calculate Asr time differently:

- **`Madhab.SHAFI`** - Asr begins when shadow length equals object height (earlier)
- **`Madhab.HANAFI`** - Asr begins when shadow length equals 2× object height (later)

```kotlin
val hanafi = CalculationParameters(
    method = CalculationMethod.MUSLIM_WORLD_LEAGUE,
    madhab = Madhab.HANAFI
)
```

---

## 🔢 Rounding Options

Control how prayer times are rounded:

```kotlin
enum class RoundingType {
    NEAREST,  // Round to nearest minute (default)
    UP,       // Always round up
    DOWN,     // Always round down
    NONE      // No rounding, keep exact time
}
```

---

## 🌐 Platform Support

| Platform | Status | Notes |
|----------|--------|-------|
| Android | ✅ Full Support | API 21+ |
| iOS | ✅ Full Support | iOS 13+ |
| JVM | ✅ Full Support | Java 8+ |
| JS | 🔄 Planned | Future release |
| Native | 🔄 Planned | Future release |

---

## 📚 API Reference

### Core Classes

#### `PrayerTimes`
Main class for calculating prayer times.

```kotlin
class PrayerTimes(
    coordinates: Coordinates,
    dateComponents: DateComponents,
    calculationParameters: CalculationParameters
)
```

#### `Coordinates`
Geographical location.

```kotlin
data class Coordinates(
    val latitude: Double,  // -90 to 90
    val longitude: Double  // -180 to 180
)
```

#### `DateComponents`
Date specification.

```kotlin
data class DateComponents(
    val year: Int,
    val month: Int,  // 1-12
    val day: Int     // 1-31
)
```

#### `Prayer`
Enumeration of prayer types.

```kotlin
enum class Prayer {
    FAJR, SUNRISE, DHUHR, ASR, MAGHRIB, ISHA
}
```

---

## 🧪 Testing

```kotlin
// Example test case
@Test
fun testMakkahPrayerTimes() {
    val makkah = Coordinates(21.4225, 39.8262)
    val date = DateComponents(2024, 1, 1)
    val params = CalculationParameters(
        method = CalculationMethod.UMM_AL_QURA
    )
    
    val times = PrayerTimes(makkah, date, params)
    
    assertNotNull(times.fajr)
    assertNotNull(times.dhuhr)
    assertNotNull(times.asr)
    assertNotNull(times.maghrib)
    assertNotNull(times.isha)
}
```

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow Kotlin coding conventions
- Add tests for new features
- Update documentation
- Ensure all tests pass before submitting

---

## ⚠️ Important Notice

**Prayer times are computational approximations** based on astronomical calculations. While this library strives for accuracy, always consult local religious authorities and mosques for the official prayer times in your area, as they may account for local sighting conditions and community practices.

---

## 📄 License

**Proprietary Software**  
Copyright © 2025 Alim Sourav. All rights reserved.

This software is proprietary and may not be used, copied, modified, or distributed without explicit permission from the copyright holder.

---

## 🙏 Acknowledgments

- Inspired by the [Adhan](https://github.com/batoulapps/adhan-java) algorithm
- Islamic calculation methods from recognized authorities worldwide
- Built with Kotlin Multiplatform

---

## 📞 Support & Contact

- **Issues**: [GitHub Issues](https://github.com/Alims-Repo/Prayer-Times-KMM/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Alims-Repo/Prayer-Times-KMM/discussions)

---

<div align="center">

**Made with ❤️ by [Alim Sourav](https://github.com/Alims-Repo)**

If this library helps your project, please consider giving it a ⭐️!

</div>
