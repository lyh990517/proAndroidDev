pluginManagement {
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
    }
}

rootProject.name = "sample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":features")
include(":domain")
include(":data")
include(":domain:network")
include(":data:network")
includeBuild("build-logic")
include(":core:design")
include(":features:core")
include(":domain:core")
include(":data:core")
include(":features:sample")
