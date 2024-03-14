package com.example.submission_dicoding_fundamental_awal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity : AppCompatActivity) : FragmentStateAdapter(activity) {

    var username = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowDetailFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowDetailFragment.ARG_POSITION, position + 1)
            putString(FollowDetailFragment.ARG_USERNAME, username)
        }
        return fragment
    }
}