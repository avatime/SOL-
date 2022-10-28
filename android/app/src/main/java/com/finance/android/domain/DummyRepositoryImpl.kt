package com.finance.android.domain

import com.finance.android.domain.repository.SampleRepository
import com.finance.android.domain.repository.UserRepository
import com.finance.android.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class DummyRepositoryImpl @Inject constructor() :
    SampleRepository,
    UserRepository {
    override fun getSampleData(): Flow<Response<Array<Int>>> = flow {
        emit(Response.Loading)
        delay(2000)
        emit(Response.Success(arrayOf(1, 2, 3)))
    }.flowOn(Dispatchers.IO)

    override suspend fun loadPhoneCode(): Flow<Response<String>> = flow {
        emit(Response.Loading)
        delay(2000)
        var code = ""
        repeat(6) {
            code += Random.nextInt(0, 10)
        }
        emit(Response.Success(code))
    }.flowOn(Dispatchers.IO)
}
