package com.example.submission_dicoding_fundamental_awal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityDetailUserBinding
import com.example.submission_dicoding_fundamental_awal.util.Event
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private var binding: ActivityDetailUserBinding? = null
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        detailUserViewModel.loading.observe(this){
            showLoadingDetail(it)
        }

        val tb = binding?.topAppBar
        setSupportActionBar(tb)

        tb?.setNavigationIcon(R.drawable.arrow___left)

        tb?.setNavigationOnClickListener{
            onBackPressed()
        }



        val login  = Event(intent.getStringExtra("login"))
        if (login.isNotEmpty()) {


            detailUserViewModel.getUserDetail(login)
            detailUserViewModel.userDetailData.observe(this) { userDetail ->
                val unWrappedDataUserDetail = userDetail.getContentIfNotHandled()

                if (unWrappedDataUserDetail != null) {

                    binding?.tvLogin?.text = unWrappedDataUserDetail.login
                    binding?.tvName?.text = unWrappedDataUserDetail.name
                    binding?.tvFollowing?.text = resources.getQuantityString(
                        R.plurals.following_plural,
                        unWrappedDataUserDetail.following,
                        unWrappedDataUserDetail.following
                    )

                    binding?.tvBio?.text = unWrappedDataUserDetail.bio?.toString()

                    binding?.tvLocation?.text = unWrappedDataUserDetail.location?.toString()

                    binding?.tvFollower?.text = resources.getQuantityString(
                        R.plurals.follower_plural,
                        unWrappedDataUserDetail.followers,
                        unWrappedDataUserDetail.followers
                    )

                    Glide.with(this)
                        .load(unWrappedDataUserDetail.avatarUrl)
                        .circleCrop()
                        .error(R.drawable.profile_1)
                        .placeholder(R.drawable.rounded_profile)
                        .into(binding?.ivUserdetail!!)

                }

            }

            Log.d("DetailUserActivity", "Received id: $login")

        } else {

            Log.e("DetailActivity", "onfailure:Data id tidak cocok")

        }

        detailUserViewModel.snackbar.observe(this){
            it.getContentIfNotHandled()?.let { snackBar ->
                Snackbar.make(window.decorView.rootView, snackBar, Snackbar.LENGTH_SHORT).show()
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, login)
        val viewPager : ViewPager2 = binding?.vpFollow!!
        viewPager.adapter = sectionPagerAdapter


        val tabs : TabLayout = binding?.tlDetail!!
        TabLayoutMediator(tabs, viewPager) {tab, position -> tab.text = resources.getString(
            TAB_TITILES[position]
        )}.attach()

        supportActionBar?.elevation = 0f

    }

    private fun showLoadingDetail(a : Boolean) {
        if (a) {
            binding?.progressBarDetail?.visibility = View.VISIBLE
        } else {
            binding?.progressBarDetail?.visibility = View.GONE
        }
    }

    companion object {
        @StringRes
        private val TAB_TITILES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

}