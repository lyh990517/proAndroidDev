package com.yunho.core.api

import com.yunho.core.dtos.SampleDto
import retrofit2.Response
import retrofit2.http.GET

interface SampleApi {
    @GET("/example")
    suspend fun getData(): Response<SampleDto>
}
