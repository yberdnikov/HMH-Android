@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    hmh("feature")
}


android {
    namespace = "com.hmh.hamyeonham.core.main"
}

dependencies {

    implementation(projects.domain.challenge)

    implementation(projects.core.common)
}
