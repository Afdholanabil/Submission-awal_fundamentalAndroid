package com.example.submission_dicoding_fundamental_awal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.GithubResponse
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import com.example.submission_dicoding_fundamental_awal.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser : LiveData<List<ItemsItem>> = _listUser

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar : LiveData<Event<String>> = _snackbar

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        getListUser()
    }

    private fun getListUser() {
        _loading.value = true
        val client = ApiConfig.getApiService().getUser("ari")
        client.enqueue(object: Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                    _snackbar.value = Event("Berhasil")
                }else {
                    Log.e(TAG, "onfailure : ${response.message()}")
                    _snackbar.value = Event("Gagal")

                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onfailure : ${t.message.toString()}")
                _snackbar.value = Event("Gagal menampilkan data")
            }

        })

    }

    fun userSearch(usernameQ : String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getUser(usernameQ)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                    _snackbar.value = Event("Berhasil")
                } else {
                    Log.e(TAG, "Onfailure : ${response.message()}")
                    _snackbar.value = Event("Gagal")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "Onfailure : ${t.message.toString()}")
                _snackbar.value = Event("Gagal menampilkan data")
            }
        })
    }

    fun searchBarKosong() {
        _snackbar.value = Event("Masukan kosong!, masukan username yang ingin anda cari")
    }

}