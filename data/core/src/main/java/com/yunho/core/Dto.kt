package com.yunho.core

interface Dto<R> {
    val transform: () -> R

    fun toDomain(): R {
        return transform()
    }
}
