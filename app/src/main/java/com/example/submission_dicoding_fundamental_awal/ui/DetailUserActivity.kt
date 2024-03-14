package com.example.submission_dicoding_fundamental_awal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2

import com.bumptech.glide.Glide
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    companion object {
        @StringRes
        private val TAB_TITILES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailUserViewModel.loading.observe(this){
            showLoadingDetail(it)
        }

        val login = intent.getStringExtra("login")
        if (login!!.isNotEmpty()) {
            detailUserViewModel.getUserDetail(login)
            detailUserViewModel.userDetailData.observe(this) { userDetail ->

                binding.tvLogin.text = userDetail.login
                binding.tvName.text = userDetail.name
                binding.tvFollower.text = resources.getQuantityString(
                    R.plurals.follower_plural,
                    userDetail.followers,
                    userDetail.followers
                )

                binding.tvFollowing.text = resources.getQuantityString(
                    R.plurals.following_plural,
                    userDetail.following,
                    userDetail.following
                )


                Glide.with(this)
                    .load(userDetail.avatarUrl)
                    .error(R.drawable.profile_1) // Placeholder untuk gambar error
                    .placeholder(R.drawable.rounded_profile) // Placeholder untuk gambar yang sedang dimuat
                    .into(binding.ivUserdetail)

            }

            Log.d("DetailUserActivity", "Received id: $login")

            Toast.makeText(this, "Berhasil membuka user dengan id : $login", Toast.LENGTH_SHORT ).show()
        } else {
            Log.e("DetailActivity", "onfailure:Data id tidak cocok")
        }


        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager : ViewPager2 = binding.vpFollow
        viewPager.adapter = sectionPagerAdapter
        val tabs : TabLayout = binding.tlDetail
        TabLayoutMediator(tabs, viewPager) {tab, position -> tab.text = resources.getString(
            TAB_TITILES[position]
        )}.attach()

        supportActionBar?.elevation = 0f

    }

    private fun showLoadingDetail(a : Boolean) {
        if (a) {
            binding.progressBarDetail.visibility = View.VISIBLE
        } else {
            binding.progressBarDetail.visibility = View.GONE
        }
    }
}