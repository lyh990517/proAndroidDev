package com.yunho.core.mvi

import com.yunho.core.error.model.ServiceError

interface UiState {
    val isLoading: Boolean
    val error: ServiceError?
}

interface UiEffect

interface UiIntent
