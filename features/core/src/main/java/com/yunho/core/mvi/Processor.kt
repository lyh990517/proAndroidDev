package com.yunho.core.mvi

interface Processor<Intent, Effect> {

    suspend fun process(
        currentIntent: Intent,
        onNextIntent: (Intent) -> Unit,
        onEffect: (Effect) -> Unit
    )
}
