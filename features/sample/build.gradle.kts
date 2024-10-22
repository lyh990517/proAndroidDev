plugins {
    alias(libs.plugins.yunho.feature)
}

android {
    namespace = "com.yunho.sample"
}

dependencies {
    implementation(projects.domain.network)
}
