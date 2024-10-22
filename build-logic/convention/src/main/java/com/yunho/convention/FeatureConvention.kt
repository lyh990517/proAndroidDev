package com.yunho.convention

import com.yunho.convention.util.addPlatform
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

class FeatureConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                with(plugins) {
                    apply(Plugins.ANDROID_LIBRARY)
                    apply(Plugins.KOTLIN_ANDROID)
                    apply(Plugins.HILT)
                    apply(Plugins.KAPT)
                    apply(Plugins.COMPOSE_COMPILER)
                }
            }

            configureAndroid(useCompose = true)

            dependencies {
                implementation(find(Dependency.APPCOMPAT))
                implementation(find(Dependency.MATERIAL))

                implementation(find(Dependency.KOTLINX_COLLECTIONS_IMMUTABLE))
                implementation(find(Dependency.LIFECYCLE_RUNTIME_COMPOSE))

                implementation(find(Dependency.ANDROIDX_COMPOSE_UI))
                implementation(find(Dependency.ANDROIDX_CORE_KTX))
                implementation(find(Dependency.ANDROIDX_ACTIVITY_COMPOSE))
                implementation(find(Dependency.ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW))
                implementation(find(Dependency.ANDROIDX_COMPOSE_MATERIAL3))
                implementation(find(Dependency.UI_GRAPHICS))
                implementation(find(Dependency.KOTPREF))
                implementation(find(Dependency.KOTPREF_ENUMS))
                addPlatform(find(Dependency.ANDROIDX_COMPOSE_BOM))

                implementation(find(Dependency.ANDROIDX_NAVIGATION_COMPOSE))
                implementation(find(Dependency.ANDROIDX_HILT_NAVIGATION_COMPOSE))

                implementation(find(Dependency.RX_KOTLIN))
                implementation(find(Dependency.RX_ANDROID))

                implementation(find(Dependency.ORBIT_COMPOSE))
                implementation(find(Dependency.ORBIT_VIEWMODEL))

                implementation(find(Dependency.HILT_ANDROID))
                kapt(find(Dependency.HILT_COMPILER))

                testImplementation(find(Dependency.JUNIT))
                androidTestImplementation(find(Dependency.JUNIT4))
                androidTestImplementation(find(Dependency.ANDROIDX_TEST_ESPRESSO_CORE))

                implementation(project(":core:design"))
                implementation(project(":features:core"))
                implementation(project(":domain:core"))
            }
        }
    }
}
