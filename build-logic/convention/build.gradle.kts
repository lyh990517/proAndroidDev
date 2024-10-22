plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

gradlePlugin {
    plugins {
        register("yunho.feature") {
            id = "yunho.feature"
            implementationClass = "com.yunho.convention.FeatureConvention"
        }
        register("yunho.domain") {
            id = "yunho.domain"
            implementationClass = "com.yunho.convention.DomainConvention"
        }
        register("yunho.data") {
            id = "yunho.data"
            implementationClass = "com.yunho.convention.DataConvention"
        }
        register("yunho.android.default") {
            id = "yunho.android.default"
            implementationClass = "com.yunho.convention.AndroidConvention"
        }
        register("yunho.android.compose") {
            id = "yunho.android.compose"
            implementationClass = "com.yunho.convention.AndroidComposeConvention"
        }
    }
}
