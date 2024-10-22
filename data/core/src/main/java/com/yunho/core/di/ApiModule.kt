package com.yunho.core.di

import com.yunho.core.api.SampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSampleService(retrofit: Retrofit): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }
}
