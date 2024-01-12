import java.util.Properties

plugins {
    hmh("application")
    hmh("compose")
    hmh("test")
    alias(libs.plugins.google.services)
    alias(libs.plugins.app.distribution)
    alias(libs.plugins.crashlytics)
}

val properties =
    Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

android {
    namespace = "com.hmh.hamyeonham"

    defaultConfig {
        applicationId = "com.hmh.hamyeonham"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "android_debug_key"
            keyPassword = "android"
            storeFile = File("${project.rootDir.absolutePath}/keystore/debug.keystore")
            storePassword = "android"
        }
        create("release") {
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
            storeFile = File("${project.rootDir.absolutePath}/keystore/release.keystore.jks")
            storePassword = properties.getProperty("storePassword")
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {

    // Feature
    implementation(projects.feature.login)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.main)
    implementation(projects.feature.mypage)

    // Domain
    implementation(projects.domain.usagestats)
    implementation(projects.domain.userinfo)
    implementation(projects.domain.login)

    // Data
    implementation(projects.data.usagestats)
    implementation(projects.data.userinfo)
    implementation(projects.data.login)

    // Core
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.database)

    // Firebase
    implementation(platform(libs.firebase))
    implementation(libs.bundles.firebase)

    // Splash
    implementation(libs.splash.screen)

    // kakao
    implementation(libs.kakao.login)
}
