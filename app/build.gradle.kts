plugins {
    hmh("application")
    hmh("compose")
    hmh("test")
    alias(libs.plugins.google.services)
    alias(libs.plugins.app.distribution)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.hmh.hamyeonham"

    defaultConfig {
        applicationId = "com.hmh.hamyeonham"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {

    // Feature
    implementation(projects.feature.statistics)

    // Domain
    implementation(projects.domain.usagestats)

    // Data
    implementation(projects.data.usagestats)

    // Core
    implementation(projects.core.common)

    // Firebase
    implementation(platform(libs.firebase))
    implementation(libs.bundles.firebase)

    // Splash
    implementation(libs.splash.screen)
}
