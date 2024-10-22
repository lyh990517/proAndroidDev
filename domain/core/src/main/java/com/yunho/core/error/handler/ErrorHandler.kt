package com.yunho.core.error.handler

import com.yunho.core.error.model.ServiceError

interface ErrorHandler {
    fun handleError(throwable: Throwable): ServiceError
}
