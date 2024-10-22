package com.yunho.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yunho.sample.SampleRoute

const val sample_route = "sample_route"

fun NavController.navigateToSampleScreen(navOptions: NavOptions? = null) {
    navigate(
        route = sample_route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.sampleScreen(onNavResult: (SampleNavResult) -> Unit) {
    composable(
        route = sample_route
    ) {
        SampleRoute(onNavResult = onNavResult)
    }
}
