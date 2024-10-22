package com.yunho.sample.viewmodel

import com.yunho.core.mvi.Reducer
import com.yunho.sample.contract.SampleIntent
import com.yunho.sample.contract.SampleScreenState
import javax.inject.Inject

class SampleReducer @Inject constructor() : Reducer<SampleScreenState, SampleIntent> {

    override suspend fun reduce(
        oldState: SampleScreenState,
        intent: SampleIntent
    ): SampleScreenState {
        return when (intent) {
            is SampleIntent.Click -> handleClickIntent(oldState = oldState, intent = intent)
            is SampleIntent.Fetch -> handleFetchIntent(oldState = oldState, intent = intent)
        }
    }

    private fun handleClickIntent(
        oldState: SampleScreenState,
        intent: SampleIntent.Click
    ): SampleScreenState {
        return oldState.copy(data = intent.data)
    }

    private fun handleFetchIntent(
        oldState: SampleScreenState,
        intent: SampleIntent.Fetch,
    ): SampleScreenState {
        return when (intent) {
            is SampleIntent.Fetch.Success -> oldState.copy(
                data = intent.data,
                isLoading = false
            )

            is SampleIntent.Fetch.Error -> oldState.copy(
                error = intent.error,
                isLoading = false
            )

            SampleIntent.Fetch.Loading -> oldState
        }
    }
}
