package com.example.submission_dicoding_fundamental_awal.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.repository.FavoriteUserRepository

class FavoriteUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _listFav = MutableLiveData<List<FavoriteUser>>()
    val listFav : LiveData<List<FavoriteUser>> = _listFav




    fun getAllFavUser(){
        _loading.value = true
        mFavoriteUserRepository.getAllFavUser().observeForever{ favList ->
            _listFav.value = favList
            _loading.value = false
        }
    }




    companion object {
        const val TAG = "FavoriteUserViewModel"
    }
}