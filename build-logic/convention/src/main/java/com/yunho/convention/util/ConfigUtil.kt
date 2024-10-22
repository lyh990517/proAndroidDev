package com.yunho.convention.util

import com.android.build.gradle.LibraryExtension
import com.yunho.convention.value.Version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid(useCompose: Boolean) {
    project.extensions.configure(LibraryExtension::class.java) {

        compileSdk = Version.TARGET_SDK

        defaultConfig {
            minSdk = Version.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        flavorDimensions.add("environment")
        productFlavors {
            create("develop") {
                dimension = "environment"
            }
            create("production") {
                dimension = "environment"
            }
        }
        buildFeatures {
            compose = useCompose
            buildConfig = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }


        project.tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = Version.JAVA_VERSION.toString()
            }
        }
    }

    project.extensions.configure(KotlinAndroidProjectExtension::class.java) {
        jvmToolchain(Version.JAVA_VERSION)
    }
}
