plugins {
    alias(libs.plugins.yunho.android.compose)
}

android {
    namespace = "com.yunho.features.core"
}

dependencies {
    implementation(libs.lifecycle.runtime.ktx)

    implementation(projects.domain.core)
}
