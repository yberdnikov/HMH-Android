@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    hmh("feature")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hmh.hamyeonham.feature.login"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // kakao
    implementation(libs.kakao.login)

    implementation (libs.dot.indicator)

}
