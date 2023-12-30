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
    implementation(libs.fragment.ktx)
    implementation(libs.retrofit)
}
