package com.yunho.sample.viewmodel

import com.yunho.core.mvi.BaseMviViewModel
import com.yunho.core.mvi.Processor
import com.yunho.core.mvi.Reducer
import com.yunho.sample.contract.SampleEffect
import com.yunho.sample.contract.SampleIntent
import com.yunho.sample.contract.SampleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleReducer: SampleReducer,
    private val sampleProcessor: SampleProcessor,
) : BaseMviViewModel<SampleScreenState, SampleIntent, SampleEffect>() {

    override fun initialState(): SampleScreenState = SampleScreenState.createInitialState()

    override val reducer: Reducer<SampleScreenState, SampleIntent>
        get() = sampleReducer

    override val processor: Processor<SampleIntent, SampleEffect>
        get() = sampleProcessor
}
