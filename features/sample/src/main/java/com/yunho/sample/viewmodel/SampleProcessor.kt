package com.yunho.sample.viewmodel

import com.yunho.core.isSuccess
import com.yunho.core.mvi.Processor
import com.yunho.network.usecase.SampleUseCase
import com.yunho.sample.contract.SampleEffect
import com.yunho.sample.contract.SampleIntent
import kotlinx.coroutines.delay
import javax.inject.Inject

class SampleProcessor @Inject constructor(
    private val sampleUseCase: SampleUseCase,
) : Processor<SampleIntent, SampleEffect> {

    override suspend fun process(
        currentIntent: SampleIntent,
        onNextIntent: (SampleIntent) -> Unit,
        onEffect: (SampleEffect) -> Unit,
    ) {
        when (currentIntent) {
            is SampleIntent.Click -> processClick(onEffect = onEffect)
            is SampleIntent.Fetch -> processFetch(
                currentIntent = currentIntent,
                onNextIntent = onNextIntent,
                onEffect = onEffect
            )
        }
    }

    private suspend fun processFetch(
        currentIntent: SampleIntent.Fetch,
        onNextIntent: (SampleIntent) -> Unit,
        onEffect: (SampleEffect) -> Unit,
    ) {
        when (currentIntent) {
            SampleIntent.Fetch.Loading -> {
                val result = sampleUseCase.getData()

                delay(3000)

                if (result.isSuccess()) {
                    val loadedData = result.data
                    onNextIntent(SampleIntent.Fetch.Success(loadedData.data))
                } else {
                    onNextIntent(SampleIntent.Fetch.Error(result.error))
                }
            }

            is SampleIntent.Fetch.Error -> {
                onEffect(SampleEffect.Toast(currentIntent.error.toString()))
            }

            is SampleIntent.Fetch.Success -> {
                onEffect(SampleEffect.Toast(currentIntent.data))
            }
        }
    }

    private fun processClick(onEffect: (SampleEffect) -> Unit) {
        onEffect(SampleEffect.Toast("click"))
    }
}
