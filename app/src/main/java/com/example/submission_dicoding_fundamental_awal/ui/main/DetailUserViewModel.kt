package com.example.submission_dicoding_fundamental_awal.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse
import com.example.submission_dicoding_fundamental_awal.data.retrofit.ApiConfig
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.repository.FavoriteUserRepository
import com.example.submission_dicoding_fundamental_awal.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : ViewModel() {

    private val _userDetailData = MutableLiveData<DetailUserResponse>()
    val userDetailData : LiveData<DetailUserResponse> = _userDetailData

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar : LiveData<Event<String>> = _snackbar

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    lateinit var userAvatarUrl: String

    private val isUserInRoom = MutableLiveData<Boolean>()

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
                    userAvatarUrl = response.body()?.avatarUrl.toString()
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

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun delete(username: String?) {
        val favUser = FavoriteUser(username ?: "")
        mFavoriteUserRepository.delete(favUser)
    }

    fun getFavoriteUserByUsername(username: String?): LiveData<FavoriteUser> {
        return mFavoriteUserRepository.getFavoriteUserByUsername(username!!)
    }

    companion object {
        private const val TAG = "DetailUserViewModel"
    }

}