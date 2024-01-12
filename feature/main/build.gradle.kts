@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    hmh("feature")
}

android {
    namespace = "com.hmh.hamyeonham.feature.main"
}

dependencies {

    // Feature
    implementation(projects.feature.challenge)
    implementation(projects.feature.mypage)

    // Core
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.viewmodel.main)

    // Domain
    implementation(projects.domain.usagestats)

    implementation(libs.bundles.navigation)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.hilt.work)
}
