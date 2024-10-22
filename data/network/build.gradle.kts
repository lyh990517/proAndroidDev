plugins {
    alias(libs.plugins.yunho.data)
}

android {
    namespace = "com.yunho.data.network"
}

dependencies {
    implementation(projects.domain.network)
}
