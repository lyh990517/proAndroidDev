plugins {
    alias(libs.plugins.yunho.data)
}

android {
    namespace = "com.yunho.websource"
}

dependencies {
    implementation(libs.jsoup)
}