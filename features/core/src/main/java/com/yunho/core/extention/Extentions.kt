package com.yunho.core.extention

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.yunho.core.mvi.UiState
import com.yunho.core.ui.FullScreenProgressIndicator

@Composable
fun UiState.SetContent(
    showBeforeFetch: Boolean = false,
    onLoading: (@Composable () -> Unit)? = null,
    onError: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    when {
        error != null -> {
            onError?.let { errorContent ->
                errorContent()
            } ?: run {
                Text(text = "${this.error}")
            }
        }

        isLoading -> {
            Box {
                if (showBeforeFetch) {
                    content()
                }
                onLoading?.let { customLoadingIndicator ->
                    customLoadingIndicator()
                } ?: run {
                    FullScreenProgressIndicator()
                }
            }
        }

        else -> {
            content()
        }
    }
}
