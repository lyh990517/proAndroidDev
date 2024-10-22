plugins {
    alias(libs.plugins.yunho.android.default)
}

android {
    namespace = "com.yunho.data.core"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    implementation(projects.domain.core)
    implementation(projects.domain.network)
}
