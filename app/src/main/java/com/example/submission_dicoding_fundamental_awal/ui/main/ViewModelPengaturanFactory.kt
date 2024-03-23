package com.example.submission_dicoding_fundamental_awal.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission_dicoding_fundamental_awal.util.SettingPreference

class ViewModelPengaturanFactory(private val preference: SettingPreference): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengaturanViewModel::class.java)) {
            return PengaturanViewModel(preference) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(preference) as T
        } else if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}