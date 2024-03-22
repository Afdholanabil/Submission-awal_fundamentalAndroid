package com.example.submission_dicoding_fundamental_awal.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityPengaturanBinding

class PengaturanActivity : AppCompatActivity() {

    private var binding : ActivityPengaturanBinding? = null
    val _binding get() = binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        binding?.switchDarkMode?.setOnCheckedChangeListener { _:CompoundButton?, iseCheked: Boolean ->
            if (iseCheked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.switchDarkMode?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.switchDarkMode?.isChecked = false
            }
        }
    }
}