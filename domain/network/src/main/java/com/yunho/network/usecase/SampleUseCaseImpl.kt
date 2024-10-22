package com.yunho.network.usecase

import com.yunho.core.ServiceResult
import com.yunho.core.asResult
import com.yunho.core.error.handler.ErrorHandler
import com.yunho.network.model.Sample
import com.yunho.network.repository.SampleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SampleUseCaseImpl @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val sampleRepository: SampleRepository
) : SampleUseCase {

    override suspend fun getData(): ServiceResult<Sample> = asResult(errorHandler) {
        sampleRepository.getData()
    }
}
