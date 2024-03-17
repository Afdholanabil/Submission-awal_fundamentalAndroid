package com.example.submission_dicoding_fundamental_awal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.submission_dicoding_fundamental_awal.data.response.followItems
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import com.example.submission_dicoding_fundamental_awal.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowDetailViewModel : ViewModel() {

    private val _listFollow = MutableLiveData<List<followItems>>()
    val listFollow : LiveData<List<followItems>> = _listFollow

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar : LiveData<Event<String>> = _snackbar

    companion object {
        private const val TAG = "FollowDetailViewModel"
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
                    _listFollow.value = response.body()
                    _snackbar.value = Event("Berhasil menampilkan list following")
                } else {
                    _snackbar.value = Event("Gagal menampilkan list following")
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
                    _listFollow.value = response.body()
                    _snackbar.value = Event("Berhasil menampilkan list following")
                } else {
                    _snackbar.value = Event("Gagal menampilkan list following")
                    Log.e(TAG, "Failed to fetch data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<followItems>>, t: Throwable) {
                Log.e(TAG, "Onfailure-Follower : ${t.message.toString()}")
            }

        })
    }

    fun followingSnackbar() {
        _snackbar.value = Event("Berhasil menampilkan list following")
    }

}