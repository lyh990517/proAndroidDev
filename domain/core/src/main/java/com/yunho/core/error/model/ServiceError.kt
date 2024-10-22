package com.yunho.core.error.model

sealed interface ServiceError {
    data object BadRequest : ServiceError

    data object UnAuthorized : ServiceError

    data object NotFound : ServiceError

    data object UnKnown : ServiceError
}
