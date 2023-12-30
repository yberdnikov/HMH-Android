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

    signingConfigs {
        getByName("debug") {
            keyAlias = "android_debug_key"
            keyPassword = "android"
            storeFile = File("${project.rootDir.absolutePath}/keystore/debug.keystore")
            storePassword = "android"
        }
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

    // Firebase
    implementation(platform(libs.firebase))
    implementation(libs.bundles.firebase)

    // Splash
    implementation(libs.splash.screen)

    implementation(projects.feature.login)

    // kakao
    implementation(libs.kakao.login)

}
