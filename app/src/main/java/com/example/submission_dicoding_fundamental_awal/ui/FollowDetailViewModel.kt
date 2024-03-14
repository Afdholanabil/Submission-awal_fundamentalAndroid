package com.example.submission_dicoding_fundamental_awal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowDetailViewModel : ViewModel() {

    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    val listFollowing : LiveData<List<ItemsItem>> = _listFollowing

    private val _listFollowers = MutableLiveData<List<ItemsItem>>()
    val listFollowers : LiveData<List<ItemsItem>> = _listFollowers

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    companion object {
        private const val TAG = "FollowDetailViewModel"
    }

    fun getListFollowing(username : String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getDetailFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "Onfailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                Log.e(TAG, "Onfailure : ${t.message.toString()}")
            }

        })
    }

    fun getListFollowers(username: String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getDetailFollowers(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _listFollowers.value = response.body()
                } else {
                    Log.e(TAG, "Onfailure-else : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                Log.e(TAG, "Onfailure : ${t.message.toString()}")
            }

        })
    }

}