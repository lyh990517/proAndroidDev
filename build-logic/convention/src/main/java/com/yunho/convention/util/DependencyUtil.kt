package com.yunho.convention.util

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.find(path: String) = libs.findLibrary(path).get()

internal fun DependencyHandlerScope.implementation(project: Project) {
    add("implementation", project)
}

internal fun DependencyHandlerScope.implementation(project: Provider<MinimalExternalModuleDependency>) {
    add("implementation", project)
}

internal fun DependencyHandlerScope.testImplementation(project: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", project)
}

internal fun DependencyHandlerScope.androidTestImplementation(project: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", project)
}

internal fun DependencyHandlerScope.addPlatform(path: Provider<MinimalExternalModuleDependency>) {
    add("implementation", platform(path))
}

internal fun DependencyHandlerScope.kapt(path: Provider<MinimalExternalModuleDependency>) {
    add("kapt", path)
}
