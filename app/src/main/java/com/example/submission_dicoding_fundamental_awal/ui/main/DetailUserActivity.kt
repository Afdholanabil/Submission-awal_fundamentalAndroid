package com.example.submission_dicoding_fundamental_awal.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityDetailUserBinding
import com.example.submission_dicoding_fundamental_awal.util.Event
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.log

class DetailUserActivity : AppCompatActivity() {

    private var binding: ActivityDetailUserBinding? = null
    private val _binding get() = binding
    private val detailUserViewModel by viewModels<DetailUserViewModel>{
        ViewModelFactory.getInstance(application)
    }
    private var favoriteUser: FavoriteUser? = null

    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        detailUserViewModel.loading.observe(this){
            showLoadingDetail(it)
        }

        val tb = binding?.topAppBar
        setSupportActionBar(tb)

        tb?.setNavigationIcon(R.drawable.arrow___left)

        tb?.setNavigationOnClickListener{
            onBackPressed()
        }


        val login  = intent.getStringExtra("login")
        Log.d("isi login Detail", login.toString())
        if (login.toString().isNotEmpty()) {
            detailUserViewModel.getUserDetail(login.toString())
            detailUserViewModel.userDetailData.observe(this) { userDetail ->

                if (userDetail != null) {
                    binding?.tvLogin?.text = userDetail.login
                    binding?.tvName?.text = userDetail.name
                    binding?.tvFollowing?.text = resources.getQuantityString(
                        R.plurals.following_plural,
                        userDetail.following,
                        userDetail.following
                    )

                    binding?.tvBio?.text = userDetail.bio?.toString()

                    binding?.tvLocation?.text = userDetail.location?.toString()

                    binding?.tvFollower?.text = resources.getQuantityString(
                        R.plurals.follower_plural,
                        userDetail.followers,
                        userDetail.followers
                    )

                    Glide.with(this)
                        .load(userDetail.avatarUrl)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.profile_1)
                        .placeholder(R.drawable.rounded_profile)
                        .into(binding?.ivUserdetail!!)
                }
            }

            Log.d("DetailUserActivity", "Received id: ${login.toString()}")
            Toast.makeText(this, "Berhasil menampilkan $login", Toast.LENGTH_SHORT).show()


        } else {

            Log.e("DetailActivity", "onfailure:Data id tidak cocok, dengan id : ${login}")

        }

        detailUserViewModel.snackbar.observe(this){
            it.getContentIfNotHandled()?.let { snackBar ->
                Snackbar.make(window.decorView.rootView, snackBar, Snackbar.LENGTH_SHORT).show()
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, login.toString())
        val viewPager : ViewPager2 = binding?.vpFollow!!
        viewPager.adapter = sectionPagerAdapter


        val tabs : TabLayout = binding?.tlDetail!!
        TabLayoutMediator(tabs, viewPager) {tab, position -> tab.text = resources.getString(
            TAB_TITILES[position]
        )}.attach()

        supportActionBar?.elevation = 0f

        binding?.fbAddFav?.setOnClickListener{
                Log.d("detailUserActivity","Berhasil insert fav si$login")
                favoriteUser = FavoriteUser(login ?: "", detailUserViewModel.userAvatarUrl ?: "")
                detailUserViewModel.insert(favoriteUser as FavoriteUser)

        }

        detailUserViewModel.getFavoriteUserByUsername(login).observe(this) { favoriteUser ->
            if (favoriteUser != null) {
                binding?.fbAddFav?.setImageResource(R.drawable.star)
            } else {
                binding?.fbAddFav?.setImageResource(R.drawable.star_broken)
            }
        }


    }

    private fun showLoadingDetail(a : Boolean) {
        if (a) {
            binding?.progressBarDetail?.visibility = View.VISIBLE
        } else {
            binding?.progressBarDetail?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailUserViewModel::class.java)
    }

    companion object {
        @StringRes
        private val TAB_TITILES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}