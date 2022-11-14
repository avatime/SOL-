package com.finance.android.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.finance.android.domain.dto.response.CardBenefitInfoResponseDto
import com.finance.android.domain.dto.response.CardBillDetailResponseDto
import com.finance.android.domain.dto.response.CardRecommendResponseDto
import com.finance.android.domain.dto.response.CardResponseDto
import com.finance.android.domain.repository.BaseRepository
import com.finance.android.domain.repository.CardRepository
import com.finance.android.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    application: Application,
    baseRepository: BaseRepository,
    private val cardRepository: CardRepository
) : BaseViewModel(application, baseRepository) {
    val cardList =
        mutableStateOf<Response<MutableList<CardResponseDto>>>(Response.Loading)

    fun myCardLoad() {
        viewModelScope.launch {
            loadMyCardList()
        }
    }

    fun getLoadState(): Response<Unit> {
        val arr = arrayOf(cardList)

        return if (arr.count { it.value is Response.Loading } != 0) {
            Response.Loading
        } else if (arr.count { it.value is Response.Failure } != 0) {
            Response.Failure(null)
        } else {
            Response.Success(Unit)
        }
    }

    private suspend fun loadMyCardList() {
        this@CardViewModel.run {
            cardRepository.getMyCardList()
        }
            .collect {
                cardList.value = it
            }
    }

    // 카드 혜택 조회

//    val cardBill = mutableStateOf<Response<CardBillResponseDto>>(Response.Loading)
    val cardBenefit = mutableStateOf<Response<MutableList<CardBenefitInfoResponseDto>>>(Response.Loading)

    fun getLoadCardBenefit(): Response<Unit> {
        val arr = arrayOf(cardBenefit)

        return if (arr.count { it.value is Response.Loading } != 0) {
            Response.Loading
        } else if (arr.count { it.value is Response.Failure } != 0) {
            Response.Failure(null)
        } else {
            Response.Success(Unit)
        }
    }

    fun loadCardBenefit(
        cardProductCode: Int
    ) {
        viewModelScope.launch {
            this@CardViewModel.run {
                cardRepository.getCardBenefit(
                    cardProductCode = cardProductCode,
                )
            }.collect {
                cardBenefit.value = it
            }
        }
    }

    // 카드 거래 기록 조회

    val cardHistory = mutableStateOf<Response<MutableList<CardBillDetailResponseDto>>>(Response.Loading)

    fun getLoadCardHistory(): Response<Unit> {
        val arr = arrayOf(cardHistory)

        return if (arr.count { it.value is Response.Loading } != 0) {
            Response.Loading
        } else if (arr.count { it.value is Response.Failure } != 0) {
            Response.Failure(null)
        } else {
            Response.Success(Unit)
        }
    }

    fun loadCardHistory(
        cdNo: String
    ) {
        viewModelScope.launch {
            this@CardViewModel.run {
                cardRepository.getCardHistory(
                    cdNo = cdNo,
                )
            }.collect {
                cardHistory.value = it
            }
        }
    }

    // 카드 추천 (신용카드 10, 체크카드 10)

    val cardRecommendList = mutableStateOf<Response<CardRecommendResponseDto>>(Response.Loading)

    fun getLoadCardRecommend(): Response<Unit> {
        val arr = arrayOf(cardRecommendList)

        return if (arr.count { it.value is Response.Loading } != 0) {
            Response.Loading
        } else if (arr.count { it.value is Response.Failure } != 0) {
            Response.Failure(null)
        } else {
            Response.Success(Unit)
        }
    }

    fun loadCardRecommend() {
        viewModelScope.launch {
            this@CardViewModel.run {
                cardRepository.getCardRecommend()
            }.collect {
                cardRecommendList.value = it
            }
        }
    }

}