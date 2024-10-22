package com.yunho.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<State : UiState, Intent : UiIntent, Effect : UiEffect> :
    ViewModel() {

    private val initialState: State by lazy { initialState() }

    protected abstract fun initialState(): State

    protected abstract val reducer: Reducer<State, Intent>

    protected abstract val processor: Processor<Intent, Effect>

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()

    private val _uiState: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<State> by lazy { _uiState }

    private val _uiEffect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val uiEffect: SharedFlow<Effect> = _uiEffect

    init {
        collectIntent()
    }

    fun sendIntent(intent: Intent) = viewModelScope.launch {
        _intent.emit(intent)
    }

    fun sendEffect(effect: Effect) = viewModelScope.launch {
        _uiEffect.emit(effect)
    }

    private fun collectIntent() = viewModelScope.launch {
        _intent.collect { intent ->
            with(intent){
                reduceState()
                processIntent()
            }
        }
    }

    private suspend fun Intent.processIntent() {
        processor.process(
            currentIntent = this,
            onNextIntent = ::sendIntent,
            onEffect = ::sendEffect
        )
    }

    private suspend fun Intent.reduceState() {
        val newState = reducer.reduce(oldState = _uiState.value, intent = this)
        setState(newState)
    }

    private fun setState(newState: State) {
        _uiState.value = newState
    }
}
