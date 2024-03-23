package com.example.submission_dicoding_fundamental_awal.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission_dicoding_fundamental_awal.util.SettingPreference
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val pref: SettingPreference) : ViewModel() {

    fun getSettingTheme(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}