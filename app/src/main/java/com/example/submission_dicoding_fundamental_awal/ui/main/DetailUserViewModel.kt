package com.example.submission_dicoding_fundamental_awal.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import com.example.submission_dicoding_fundamental_awal.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    private val _userDetailData = MutableLiveData<Event<DetailUserResponse>>()
    val userDetailData : LiveData<Event<DetailUserResponse>> = _userDetailData

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar : LiveData<Event<String>> = _snackbar

    fun getUserDetail(login : Event<String?>) {
        _loading.value = true
        val client = ApiConfig.getApiService().getDetailUser(login.getContentIfNotHandled() ?: "")
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _userDetailData.value = Event(response.body()!!)
                    _snackbar.value = Event("Berhasil menampilkan detail user")
                } else {
                    Log.e(TAG, "Onfailure-else : ${response.message()}& code : ${response.code()}")
                    _snackbar.value = Event("Gagal menampilkan detail user")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _snackbar.value = Event("Gagal memuat data")

            }

        })
    }

    companion object {
        private const val TAG = "DetailUserViewModel"
    }

}