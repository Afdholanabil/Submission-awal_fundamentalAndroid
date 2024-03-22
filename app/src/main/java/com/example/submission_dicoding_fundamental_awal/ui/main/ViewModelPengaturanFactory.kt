package com.example.submission_dicoding_fundamental_awal.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission_dicoding_fundamental_awal.util.SettingPreference

class ViewModelPengaturanFactory(private val preference: SettingPreference): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelPengaturanFactory? = null

        @JvmStatic
        fun getInstance(preference: SettingPreference): ViewModelPengaturanFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelPengaturanFactory::class.java){
                    INSTANCE = ViewModelPengaturanFactory(preference)
                }
            }
            return INSTANCE as ViewModelPengaturanFactory
        }
    }
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengaturanViewModel::class.java)) {
            return PengaturanViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}