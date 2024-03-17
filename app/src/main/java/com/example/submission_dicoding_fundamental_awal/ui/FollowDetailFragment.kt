package com.example.submission_dicoding_fundamental_awal.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.submission_dicoding_fundamental_awal.databinding.FragmentFollowDetailBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_dicoding_fundamental_awal.data.response.followItems
import com.google.android.material.snackbar.Snackbar


class FollowDetailFragment : Fragment() {

    private lateinit var binding : FragmentFollowDetailBinding
    private val followViewModel: FollowDetailViewModel by viewModels()

    private var position : Int = 0
    private var username : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION.toString())
            username = it.getString(ARG_USERNAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUserFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvUserFollow.addItemDecoration(itemDecoration)

        followViewModel.loading.observe(viewLifecycleOwner) {
            showLoading(it)
        }



        if (position == 1) {

            followViewModel.listFollow.observe(viewLifecycleOwner) { data ->
                if (data != null){
                    setListData(data)
                    setTextifNull(data)
                    Log.d(TAG, "Data following: $data")
                } else {
                    binding.tvFollow.visibility = View.VISIBLE
                }

            }
            followViewModel.getListFollowing(username)
            followViewModel.followingSnackbar()

        } else {
            followViewModel.listFollow.observe(viewLifecycleOwner) { data ->
                if (data != null){
                    setListData(data)
                    setTextifNull(data)
                    Log.d(TAG, "Data followers: $data")
                } else {
                    binding.tvFollow.visibility = View.VISIBLE
                }

            }
            followViewModel.getListFollowers(username)
        }
        followViewModel.snackbar.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { message ->
                Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val ARG_POSITION = 0
        const val ARG_USERNAME = "username"
        const val TAG = "FollowDetailFragment"

    }

    private fun setListData(data: List<followItems>) {
        val adapter = UserFollowAdapter(data, requireActivity())
        binding.rvUserFollow.adapter = adapter
    }

    private fun setTextifNull(data: List<followItems>) {
        val adapter = UserFollowAdapter(data, requireActivity())
        binding.rvUserFollow.adapter = adapter

        if (data.isEmpty()) {
            binding.tvFollow.visibility = View.VISIBLE
        } else {
            binding.tvFollow.visibility = View.GONE
        }
        }



    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBarFollow.visibility =View.VISIBLE
        } else {
            binding.progressBarFollow.visibility = View.GONE
        }
    }
}