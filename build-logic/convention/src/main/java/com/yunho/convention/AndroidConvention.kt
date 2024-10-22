package com.yunho.convention

import com.yunho.convention.util.androidTestImplementation
import com.yunho.convention.util.configureAndroid
import com.yunho.convention.util.find
import com.yunho.convention.util.implementation
import com.yunho.convention.util.kapt
import com.yunho.convention.util.testImplementation
import com.yunho.convention.value.Dependency
import com.yunho.convention.value.Plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                with(plugins) {
                    apply(Plugins.ANDROID_LIBRARY)
                    apply(Plugins.KOTLIN_ANDROID)
                    apply(Plugins.KAPT)
                }
            }

            configureAndroid(useCompose = false)

            dependencies {
                implementation(find(Dependency.ANDROIDX_CORE_KTX))
                implementation(find(Dependency.APPCOMPAT))
                implementation(find(Dependency.MATERIAL))

                testImplementation(find(Dependency.JUNIT))
                androidTestImplementation(find(Dependency.JUNIT4))
                androidTestImplementation(find(Dependency.ANDROIDX_TEST_ESPRESSO_CORE))

                implementation(find(Dependency.HILT_ANDROID))
                kapt(find(Dependency.HILT_COMPILER))
            }
        }
    }
}
