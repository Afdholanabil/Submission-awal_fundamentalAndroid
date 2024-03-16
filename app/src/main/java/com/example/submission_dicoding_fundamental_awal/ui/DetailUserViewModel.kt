package com.example.submission_dicoding_fundamental_awal.ui

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse
import com.example.submission_dicoding_fundamental_awal.data.response.followItems
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

    private val _listFollow = MutableLiveData<List<followItems>>()
    val listFollow : LiveData<List<followItems>> = _listFollow

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
                    Log.e(TAG, "Onfailure-else : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getListFollowing(username : String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<followItems>> {
            override fun onResponse(
                call: Call<List<followItems>>,
                response: Response<List<followItems>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d(TAG, "Data from API: $data")
                    _listFollow.value = data!!
//                    Log.d(TAG, "OnResponse-Following($username) : ${response.message()}")
                } else {
//                    Log.e(TAG, "Onfailure-Following-else($username) : ${response.message()}")
                    Log.e(TAG, "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<followItems>>, t: Throwable) {
                Log.e(TAG, "Onfailure-Following : ${t.message.toString()}")
            }

        })
    }

    fun getListFollowers(username: String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<followItems>> {
            override fun onResponse(
                call: Call<List<followItems>>,
                response: Response<List<followItems>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d(TAG, "Data from API: $data")
                    _listFollow.value = data!!
                    //                    Log.d(TAG, "OnResponse-Follower($username) : ${response.message()}")
                } else {
//                    Log.e(TAG, "Onfailure-Follower-else($username) : ${response.message()}")
                    Log.e(TAG, "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<followItems>>, t: Throwable) {
                Log.e(TAG, "Onfailure-Follower : ${t.message.toString()}")
            }

        })
    }
}