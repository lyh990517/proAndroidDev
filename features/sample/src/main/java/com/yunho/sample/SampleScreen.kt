package com.yunho.sample

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yunho.core.extention.SetContent
import com.yunho.sample.contract.SampleEffect
import com.yunho.sample.contract.SampleIntent
import com.yunho.sample.contract.SampleScreenState
import com.yunho.sample.navigation.SampleNavResult
import com.yunho.sample.viewmodel.SampleViewModel
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SampleRoute(
    viewModel: SampleViewModel = hiltViewModel(),
    onNavResult: (SampleNavResult) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SampleScreen(
        uiState = uiState,
        uiEffect = viewModel.uiEffect,
        sendEffect = viewModel::sendEffect,
        sendIntent = viewModel::sendIntent,
        onNavResult = onNavResult
    )
}

@Composable
fun SampleScreen(
    uiState: SampleScreenState,
    uiEffect: SharedFlow<SampleEffect>,
    sendEffect: (SampleEffect) -> Unit,
    sendIntent: (SampleIntent) -> Unit,
    onNavResult: (SampleNavResult) -> Unit
) {
    val context = LocalContext.current

    BackHandler {
        onNavResult(SampleNavResult.MoveBack)
    }

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is SampleEffect.Toast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        if (uiState.isLoading) {
            sendIntent(SampleIntent.Fetch.Loading)
            sendEffect(SampleEffect.Toast("fetch start"))
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "TopBar"
                )
            }
        },
        content = { padding ->
            uiState.SetContent {
                SampleScreenContent(
                    modifier = Modifier
                        .padding(padding),
                    uiState = uiState,
                    sendIntent = sendIntent
                )
            }
        }
    )
}

@Composable
private fun SampleScreenContent(
    modifier: Modifier = Modifier,
    uiState: SampleScreenState,
    sendIntent: (SampleIntent) -> Unit
) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = {
                counter += 1
                sendIntent(SampleIntent.Click("Click: $counter"))
            }
        ) {
            Text(text = (uiState.data))
        }
    }
}
