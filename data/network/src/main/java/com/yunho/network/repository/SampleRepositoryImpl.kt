package com.yunho.network.repository

import com.yunho.core.extension.parseResponse
import com.yunho.network.datasource.SampleDataSource
import com.yunho.network.model.Sample
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource
) : SampleRepository {
    override suspend fun getData(): Sample =
        sampleDataSource
            .getData()
            .parseResponse()
            .toDomain()
}
