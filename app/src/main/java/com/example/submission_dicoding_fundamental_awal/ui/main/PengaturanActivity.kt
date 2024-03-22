package com.example.submission_dicoding_fundamental_awal.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityPengaturanBinding
import com.example.submission_dicoding_fundamental_awal.util.SettingPreference
import com.example.submission_dicoding_fundamental_awal.util.dataStore

class PengaturanActivity : AppCompatActivity() {

    private var binding : ActivityPengaturanBinding? = null
    val _binding get() = binding

    private val pengaturanViewModel by viewModels<PengaturanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val pref = SettingPreference.getInstance(application.dataStore)
        val pengaturanViewModel = ViewModelProvider(this, ViewModelPengaturanFactory(pref)).get(
            PengaturanViewModel::class.java
        )
        pengaturanViewModel.getSettingTheme().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.switchDarkMode?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.switchDarkMode?.isChecked = false
            }
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            pengaturanViewModel.saveThemeSetting(isChecked)
        }
    }
}