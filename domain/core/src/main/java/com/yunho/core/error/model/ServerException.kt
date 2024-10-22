package com.yunho.core.error.model

data class ServerException(val data: String, val code: Int) : Throwable(data)
