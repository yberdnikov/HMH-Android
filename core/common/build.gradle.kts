@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    hmh("feature")
    hmh("compose")
}

android {
    namespace = "com.hmh.hamyeonham.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.viewmodel.selection)

    implementation(libs.retrofit)
}
