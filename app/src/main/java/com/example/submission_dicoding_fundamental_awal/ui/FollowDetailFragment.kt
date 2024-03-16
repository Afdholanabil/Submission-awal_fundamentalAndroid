package com.example.submission_dicoding_fundamental_awal.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.databinding.FragmentFollowDetailBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.data.response.followItems


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowDetailFragment : Fragment() {

    private lateinit var binding : FragmentFollowDetailBinding
    private val followViewModel: DetailUserViewModel by viewModels()

    private var position : Int = 0
    private var username : String = ""
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



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
    ): View? {
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

            followViewModel.listFollow.observe(viewLifecycleOwner) {
                setListData(it)
                Log.d(TAG, "Data following: $it")
            }
            followViewModel.getListFollowing(username)

        } else {
            followViewModel.listFollow.observe(viewLifecycleOwner) {
                setListData(it)
                Log.d(TAG, "Data followers: $it")
            }
            followViewModel.getListFollowers(username)

        }

    }

    companion object {
        const val ARG_POSITION = 0
        const val ARG_USERNAME = "username"
        const val TAG = "FollowDetailFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FollowDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(positionN: Int, usernameN: String) =
            FollowDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(position.toString(), positionN)
                    putString(ARG_USERNAME, username)
                }

            }
    }

    private fun setListData(data: List<followItems>) {
        val adapter = UserFollowAdapter(data, requireActivity())
        binding.rvUserFollow.adapter = adapter


    }



    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBarFollow.visibility =View.VISIBLE
        } else {
            binding.progressBarFollow.visibility = View.GONE
        }
    }
}