package com.example.submission_dicoding_fundamental_awal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse

class DetailUserViewModel : ViewModel() {

    private val _userDetailData = MutableLiveData<DetailUserResponse>()
    val userDetailData : LiveData<DetailUserResponse> = _userDetailData

    fun getUserDetail(id : Int) {

    }
}