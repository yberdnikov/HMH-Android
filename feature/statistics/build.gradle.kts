@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    hmh("feature")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hmh.hamyeonham.feature.statistics"
}

dependencies {
    implementation(projects.domain.usagestats)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
}
