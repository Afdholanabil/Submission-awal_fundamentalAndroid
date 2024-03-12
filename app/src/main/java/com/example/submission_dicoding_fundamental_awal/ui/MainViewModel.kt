package com.example.submission_dicoding_fundamental_awal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.GithubResponse
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser : LiveData<List<ItemsItem>> = _listUser

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _githubResponse = MutableLiveData<GithubResponse>()
    val githubResponse : LiveData<GithubResponse> = _githubResponse

    companion object {
        private const val TAG = "MainViewModel"
        private const val USER_HINT = "ari"
    }

    init {

    }

    private fun getListUser() {
        _loading.value = true
        val client = ApiConfig.getApiService().getUser(USER_HINT)
        client.enqueue(object: Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                }else {
                    Log.e(TAG, "onfailure L ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onfailure : ${t.message.toString()}")
            }

        })

    }
}