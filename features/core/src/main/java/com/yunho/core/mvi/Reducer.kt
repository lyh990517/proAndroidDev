package com.yunho.core.mvi

interface Reducer<State : UiState, Intent : UiIntent> {
    suspend fun reduce(oldState: State, intent: Intent): State
}
