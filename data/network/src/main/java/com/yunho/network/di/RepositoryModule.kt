package com.yunho.network.di

import com.yunho.network.repository.SampleRepository
import com.yunho.network.repository.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository
}
