package com.example.submission_dicoding_fundamental_awal.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission_dicoding_fundamental_awal.util.Event

class SectionPagerAdapter(activity : AppCompatActivity, private val username: Event<String?>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowDetailFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowDetailFragment.ARG_POSITION.toString(), position + 1)
            username.peekContent().let { nonNull ->
                putString(FollowDetailFragment.ARG_USERNAME, nonNull)
            }

        }
        return fragment
    }
}