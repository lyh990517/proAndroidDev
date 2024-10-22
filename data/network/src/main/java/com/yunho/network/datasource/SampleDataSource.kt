package com.yunho.network.datasource

import com.yunho.core.dtos.SampleDto
import retrofit2.Response
import javax.inject.Inject

class SampleDataSource @Inject constructor() {

    fun getData(): Response<SampleDto> = Response.success(SampleDto(data = "data from dummy response"))
}
