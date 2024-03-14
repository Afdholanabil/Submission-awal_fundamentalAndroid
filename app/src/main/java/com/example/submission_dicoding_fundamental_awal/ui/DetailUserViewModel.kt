package com.example.submission_dicoding_fundamental_awal.ui

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    private val _userDetailData = MutableLiveData<DetailUserResponse>()
    val userDetailData : LiveData<DetailUserResponse> = _userDetailData

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    companion object {
        private const val TAG = "DetailUserViewModel"
    }



    fun getUserDetail(login : String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getDetailUser(login)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _userDetailData.value = response.body()
                } else {
                    Log.e(TAG, "Onfailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}