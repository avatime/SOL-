package com.finance.android.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.android.domain.dto.response.RecentTradeResponseDto
import com.finance.android.domain.repository.BankRepository
import com.finance.android.domain.repository.RemitRepository
import com.finance.android.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemitViewModel @Inject constructor(
    private val remitRepository: RemitRepository
) : ViewModel() {

    private val _recommendedAccountData = mutableStateOf<Response<MutableList<RecentTradeResponseDto>>>(Response.Loading)
    val recommendedAccountData = _recommendedAccountData

    fun getRecommendedAccountData() {
        viewModelScope.launch {
            remitRepository.getRecommendedAccount().collect {
                _recommendedAccountData.value = it
                //
            }
        }
    }

    fun onClickBookmark(key: Any){
        _recommendedAccountData.value
    }
}