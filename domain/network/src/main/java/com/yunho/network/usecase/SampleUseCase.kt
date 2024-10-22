package com.yunho.network.usecase

import com.yunho.core.ServiceResult
import com.yunho.network.model.Sample

interface SampleUseCase {
    suspend fun getData(): ServiceResult<Sample>
}
