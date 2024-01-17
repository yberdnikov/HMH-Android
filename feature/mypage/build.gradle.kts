@Suppress("DSL_SCOPE_VIOLATION") plugins {
    hmh("feature")
}

android {
    namespace = "com.hmh.hamyeonham.feature.mypage"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.fragment.ktx)
    implementation(libs.retrofit)

    // core
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.viewmodel.main)
    implementation(projects.core.network)

    // domain
    implementation(projects.domain.userinfo)
    implementation(projects.domain.login)
}
