package com.yunho.sample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yunho.sample.navigation.SampleNavResult
import com.yunho.sample.navigation.sampleScreen
import com.yunho.sample.navigation.sample_route

@Composable
fun SampleApp() {
    SampleAppGraph()
}

@Composable
fun SampleAppGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(navController = navHostController, startDestination = sample_route) {
        sampleScreen { result ->
            when (result) {
                SampleNavResult.MoveBack -> {
                    navHostController.popBackStack(
                        destinationId = navHostController.graph.id,
                        inclusive = true
                    )
                }
            }
        }
    }
}
