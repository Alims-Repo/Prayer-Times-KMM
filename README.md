# Prayer Times KMM: Precision Islamic Prayer Time Calculations

## 🌟 Overview

Prayer Times KMM is a cutting-edge Kotlin Multiplatform library designed to provide accurate, reliable, and customizable Islamic prayer time calculations for developers worldwide. Developed with precision and flexibility in mind, this library empowers applications to seamlessly integrate prayer time calculations across multiple platforms.

## 📋 Table of Contents

- [Features](#-key-features)
- [Installation](#-installation)
- [Getting Started](#-getting-started)
- [Advanced Usage](#-advanced-usage)
- [Calculation Methods](#-supported-calculation-methods)
- [Customization](#-deep-customization)
- [Platform Support](#-platform-support)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Key Features

### 🌍 Global Precision
- Accurate prayer time calculations for any global location
- Support for complex geographical scenarios
- Handles various astronomical calculations

### 🕰️ Comprehensive Time Calculations
- Calculate all five daily prayers (Fajr, Sunrise, Dhuhr, Asr, Maghrib, Isha)
- Precise solar time computations
- Seasonal and latitude-based adjustments

### 🔧 Flexible Configuration
- Multiple calculation methods
- Customizable prayer time adjustments
- Support for different Madhabs (Islamic schools of thought)
- Advanced twilight angle configuration

### 🌐 Cross-Platform Compatibility
- Kotlin Multiplatform support
- Seamless integration with Android, iOS, and JVM
- Consistent API across all platforms

## 🚀 Installation

### Gradle Setup

#### Kotlin DSL (`build.gradle.kts`)
```kotlin
dependencies {
    implementation("com.alim:prayer-times-kmm:1.0.0")
}
```

#### Groovy (`build.gradle`)
```groovy
dependencies {
    implementation 'com.alim:prayer-times-kmm:1.0.0'
}
```

## 🎬 Getting Started

### Basic Usage Example

```kotlin
// Define geographical coordinates
val coordinates = Coordinates(
    latitude = 21.4225,   // Makkah Latitude
    longitude = 39.8262   // Makkah Longitude
)

// Specify date components
val dateComponents = DateComponents(
    year = 2024,
    month = 3,
    day = 26
)

// Configure calculation parameters
val calculationParameters = CalculationParameters(
    method = CalculationMethod.UMM_AL_QURA,
    madhab = Madhab.SHAFI,
    rounding = RoundingType.NEAREST
)

// Calculate prayer times
val prayerTimes = PrayerTimes(
    coordinates = coordinates,
    dateComponents = dateComponents,
    calculationParameters = calculationParameters
)

// Access prayer times
println("Fajr: ${prayerTimes.fajr}")
println("Sunrise: ${prayerTimes.sunrise}")
println("Dhuhr: ${prayerTimes.dhuhr}")
println("Asr: ${prayerTimes.asr}")
println("Maghrib: ${prayerTimes.maghrib}")
println("Isha: ${prayerTimes.isha}")
```

## 🔬 Advanced Usage

### Dynamic Prayer Tracking

```kotlin
// Get current and next prayer
val currentPrayer = prayerTimes.currentPrayer(currentInstant)
val nextPrayer = prayerTimes.nextPrayer(currentInstant)

// Retrieve specific prayer time
val fajrTime = prayerTimes.timeForPrayer(Prayer.FAJR)
```

### Custom Adjustments

```kotlin
val customParameters = CalculationParameters(
    method = CalculationMethod.MUSLIM_WORLD_LEAGUE,
    prayerAdjustments = PrayerAdjustments(
        fajr = 2,     // Add 2 minutes to Fajr
        dhuhr = -1,   // Subtract 1 minute from Dhuhr
        asr = 3       // Add 3 minutes to Asr
    ),
    methodAdjustments = MethodAdjustments(
        fajr = 1,     // Additional method-specific adjustment
        isha = -2     // Method-specific Isha adjustment
    )
)
```

## 🌈 Supported Calculation Methods

1. Muslim World League
2. Egyptian General Authority of Survey
3. Umm Al-Qura University, Makkah
4. Gulf Region
5. Kuwait
6. Qatar
7. Singapore
8. Moon Sighting Committee
9. Karachi
10. Tehran
11. North America

## 🛠 Deep Customization

### Twilight Angle Configuration
- Custom Fajr and Isha calculation angles
- Seasonal adjustments
- Latitude-based twilight calculations

### Madhab Support
- Shafi
- Hanafi
- Custom shadow length calculations

### Rounding Options
- Nearest
- Up
- Down
- None

## 🖥️ Platform Support

| Platform    | Supported | Notes |
|-------------|-----------|-------|
| Android     | ✅ | Full support |
| iOS         | ✅ | Full support |
| JVM         | ✅ | Desktop and server applications |

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

Proprietary software. Copyright © 2025 Alim Sourav. All rights reserved.

## ⚠️ Disclaimer

Prayer times are computational approximations. Always consult local religious authorities for precise prayer times.

---

**Made with ❤️ by Alim Sourav**

[GitHub Repository](https://github.com/Alims-Repo/Prayer-Times-KMM)
