package com.yunho.sample.contract

import com.yunho.core.error.model.ServiceError
import com.yunho.core.mvi.UiEffect
import com.yunho.core.mvi.UiIntent
import com.yunho.core.mvi.UiState

data class SampleScreenState(
    val data: String,
    override val isLoading: Boolean,
    override val error: ServiceError?
) : UiState {
    companion object {
        fun createInitialState() = SampleScreenState(
            data = "",
            isLoading = true,
            error = null
        )
    }
}

sealed interface SampleEffect : UiEffect {
    data class Toast(val message: String) : SampleEffect
}

sealed interface SampleIntent : UiIntent {
    sealed interface Fetch : SampleIntent {
        data object Loading : Fetch

        data class Success(val data: String) : Fetch

        data class Error(val error: ServiceError) : Fetch
    }

    data class Click(val data: String) : SampleIntent
}
