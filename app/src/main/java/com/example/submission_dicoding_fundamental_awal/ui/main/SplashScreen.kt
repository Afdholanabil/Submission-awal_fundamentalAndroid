package com.example.submission_dicoding_fundamental_awal.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.ActivitySplashScreenBinding
import com.example.submission_dicoding_fundamental_awal.util.SettingPreference
import com.example.submission_dicoding_fundamental_awal.util.dataStore

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val _binding get() = binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val pref = SettingPreference.getInstance(application.dataStore)
        val splashScreenViewModel = ViewModelProvider(this, ViewModelPengaturanFactory(pref)).get(
            SplashScreenViewModel::class.java
        )

        splashScreenViewModel.getSettingTheme().observe(this) {isDarkThemeActiuve: Boolean ->
            if (isDarkThemeActiuve) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.ivSplash.alpha = 0f
        binding.ivSplash.animate().setDuration(3000).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}