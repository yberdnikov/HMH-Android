enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "HMH-Android"
include(":app")

include(":feature:login")
include(":feature:challenge")
include(":feature:onboarding")
include(":feature:main")
include(":feature:mypage")

include(":data:usagestats")
include(":data:onboarding")
include(":data:userinfo")
include(":data:login")

include(":domain:userinfo")
include(":domain:usagestats")
include(":domain:challenge")
include(":domain:login")
include(":domain:onboarding")

include(":core:common")
include(":core:database")
include(":core:designsystem")
include(":core:viewmodel:main")
include(":core:network")
include(":data:device")
include(":data:challenge")
