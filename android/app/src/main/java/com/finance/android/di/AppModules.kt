package com.finance.android.di

import com.finance.android.domain.DummyRepositoryImpl
import com.finance.android.domain.RetrofitClient
import com.finance.android.domain.repository.SampleRepository
import com.finance.android.domain.repository.UserRepository
import com.finance.android.domain.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    fun provideSampleRepository(): SampleRepository = DummyRepositoryImpl()

    @Provides
    fun provideUserRepository(): UserRepository = DummyRepositoryImpl()

    @Provides
    fun provideRetrofit(): Retrofit = RetrofitClient().instance

    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)
}
