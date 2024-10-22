package com.yunho.core.error.handler

import com.yunho.core.error.model.ServerException
import com.yunho.core.error.model.ServiceError
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun handleError(throwable: Throwable): ServiceError {
        return when (throwable) {
            is ServerException -> {
                val code = throwable.code

                when (code) {
                    400 -> ServiceError.BadRequest
                    401 -> ServiceError.UnAuthorized
                    404 -> ServiceError.NotFound
                    else -> ServiceError.UnKnown
                }
            }

            else -> ServiceError.UnKnown
        }
    }
}
