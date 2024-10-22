package com.yunho.core.extension

import com.yunho.core.error.model.ServerException
import retrofit2.Response

fun <T> Response<T>?.parseResponse(): T {
    return when (this?.isSuccessful) {
        true -> this.body() ?: throw NoSuchElementException("No body was found.")
        else -> throw ServerException(
            data = this?.message() ?: "null",
            code = this?.code() ?: -1
        )
    }
}
