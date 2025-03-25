import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

kotlin {

    jvm()

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        jvmMain.dependencies {

        }

        commonMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


android {
    namespace = "com.alim.prayerapi"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.alims-repo",
        artifactId = "prayer-times-kmm",
        version = "1.0.1-beta"
    )

    pom {
        name.set("Prayer Times KMM")
        description.set("A lightweight and accurate Kotlin Multiplatform library for calculating Islamic prayer times, inspired by the Adhan algorithm and optimized for Android, iOS, JVM, and beyond.")
        inceptionYear.set("2025")
        url.set("https://github.com/Alims-Repo/Prayer-Times-KMM")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("alim")
                name.set("Alim Sourav")
                email.set("sourav.0.alim@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/Alims-Repo/Prayer-Times-KMM")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
