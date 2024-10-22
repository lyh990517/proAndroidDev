package com.yunho.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FullScreenProgressIndicator(
    modifier: Modifier = Modifier,
    barColor: Color = Color.Blue
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = barColor,
            modifier =
            Modifier
                .align(Alignment.Center)
        )
    }
}
