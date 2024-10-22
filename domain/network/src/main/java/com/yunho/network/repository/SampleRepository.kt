package com.yunho.network.repository

import com.yunho.network.model.Sample

interface SampleRepository {
    suspend fun getData(): Sample
}
