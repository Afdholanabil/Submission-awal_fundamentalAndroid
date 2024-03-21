package com.example.submission_dicoding_fundamental_awal.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.repository.FavoriteUserRepository

class FavoriteUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getAllFavUser(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavUser()
}