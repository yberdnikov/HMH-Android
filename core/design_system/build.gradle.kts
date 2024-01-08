@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    hmh("feature")
}

android {
    namespace = "com.hmh.hamyeonham.core.design_system"
}

dependencies {
    // Splash
    implementation(libs.splash.screen)
}
