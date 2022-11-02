package com.finance.android.di

import com.finance.android.domain.DummyRepositoryImpl
import com.finance.android.domain.RetrofitClient
import com.finance.android.domain.repository.*
import com.finance.android.domain.service.*
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
    fun provideUserRepository(
        userService: UserService
    ): UserRepository = UserRepositoryImpl(userService)

    @Provides
    fun provideRemitRepository (
        remitService: RemitService
    ) : RemitRepository = DummyRepositoryImpl()

    @Provides
    fun provideBankRepository (
        bankService: BankService
    ) : BankRepository = DummyRepositoryImpl()

    @Provides
    fun provideCardRepository(
        cardService: CardService
    ): CardRepository = CardRepositoryImpl(cardService)

    @Provides
    fun provideInsuranceRepository(
        insuranceService: InsuranceService
    ): InsuranceRepository = InsuranceRepositoryImpl(insuranceService)

    @Provides
    fun provideStockRepository(
        stockService: StockService
    ): StockRepository = StockRepositoryImpl(stockService)

    @Provides
    fun provideBaseRepository(baseService: BaseService): BaseRepository = BaseRepositoryImpl(baseService)

    @Provides
    fun provideRetrofit(): Retrofit = RetrofitClient.getInstance()

    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    fun provideRemitService(
        retrofit: Retrofit
    ) : RemitService = retrofit.create(RemitService::class.java)

    @Provides
    fun provideBankService(
        retrofit: Retrofit
    ) : BankService = retrofit.create(BankService::class.java)

    @Provides
    fun provideBaseService(
        retrofit: Retrofit
    ): BaseService = retrofit.create(BaseService::class.java)

    @Provides
    fun provideCardService(
        retrofit: Retrofit
    ): CardService = retrofit.create(CardService::class.java)

    @Provides
    fun provideInsuranceService(
        retrofit: Retrofit
    ): InsuranceService = retrofit.create(InsuranceService::class.java)

    @Provides
    fun provideStockService(
        retrofit: Retrofit
    ): StockService = retrofit.create(StockService::class.java)
}
