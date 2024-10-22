package com.yunho.network.di

import com.yunho.network.usecase.SampleUseCase
import com.yunho.network.usecase.SampleUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun bindsSampleUseCase(
        sampleUseCaseImpl: SampleUseCaseImpl
    ): SampleUseCase
}
