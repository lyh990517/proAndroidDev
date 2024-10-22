package com.yunho.core

import com.yunho.core.error.model.ServiceError

sealed interface ServiceResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ServiceResult<T>

    data class Failure(val error: ServiceError) : ServiceResult<Nothing>
}
