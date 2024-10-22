package com.yunho.core

import com.yunho.core.error.handler.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <T : Any> ServiceResult<T>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is ServiceResult.Success)
        returns(false) implies (this@isSuccess is ServiceResult.Failure)
    }

    return this is ServiceResult.Success
}

fun <T : Any> ServiceResult<T>.getOrNull(): T? {
    return if (isSuccess()) {
        data
    } else {
        null
    }
}

inline fun <R : Any, T : Any> ServiceResult<T>.map(transform: (T) -> R): ServiceResult<R> {
    return when (this) {
        is ServiceResult.Success -> ServiceResult.Success(transform(data))
        is ServiceResult.Failure -> this
    }
}

inline fun <R : Any, T : Any> ServiceResult<T>.flatMap(
    transform: (T) -> ServiceResult<R>
): ServiceResult<R> {
    return when (this) {
        is ServiceResult.Success -> transform(data)
        is ServiceResult.Failure -> this
    }
}

inline fun <T : Any> MutableStateFlow<ServiceResult<T>>.updateOnSuccess(function: (T) -> T) {
    update {
        if (it.isSuccess()) {
            ServiceResult.Success(function(it.data))
        } else {
            it
        }
    }
}

inline fun <T : Any> ServiceResult<T>.onSuccess(
    function: (T) -> Unit,
): ServiceResult<T> {
    if (this.isSuccess()) {
        function.invoke(this.data)
    }

    return this
}

inline fun <T : Any> ServiceResult<T>.onFailure(
    function: (ServiceResult.Failure) -> Unit,
): ServiceResult<T> {
    if (this is ServiceResult.Failure) {
        function.invoke(this)
    }

    return this
}

inline fun <T, R : Any> T.asResult(
    errorHandler: ErrorHandler,
    block: T.() -> R
): ServiceResult<R> {
    return try {
        ServiceResult.Success(block())
    } catch (e: Throwable) {
        ServiceResult.Failure(errorHandler.handleError(e))
    }
}
