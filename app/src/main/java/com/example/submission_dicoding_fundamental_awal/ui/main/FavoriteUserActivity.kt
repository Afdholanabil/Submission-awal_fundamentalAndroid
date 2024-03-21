package com.example.submission_dicoding_fundamental_awal.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityFavoriteUserBinding

class FavoriteUserActivity : AppCompatActivity() {

    private var binding: ActivityFavoriteUserBinding? = null
    val _binding get() = binding
    private val favoriteViewModel by viewModels<FavoriteUserViewModel>{
        ViewModelFactory.getInstance(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val layoutManager = LinearLayoutManager(this)
        binding?.rvUserFav?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this,layoutManager.orientation)
        binding?.rvUserFav?.addItemDecoration(itemDecoration)

        val adapter = FavUserAdapter(emptyList(), this)
        binding?.rvUserFav?.adapter = adapter

        adapter.setOnClickListener { selectedItem ->
            val intent = Intent(this, DetailUserActivity::class.java)
            intent.putExtra("login",selectedItem.username)
            startActivity(intent)
        }

        favoriteViewModel.loading.observe(this) { loading ->
            showLoading(loading)
        }

        favoriteViewModel.listFav.observe(this) {list ->
            setListFav(list)
            Log.d("FavoriteUserActivity","Berhasil mengambil data")
        }

        favoriteViewModel.getAllFavUser()

    }

    private fun setListFav(list: List<FavoriteUser>) {
        (binding?.rvUserFav?.adapter as? FavUserAdapter)?.apply {
            userFavList = list
            notifyDataSetChanged()
        }
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding?.progressBarFav?.visibility = View.VISIBLE
        } else {
            binding?.progressBarFav?.visibility = View.GONE
        }
    }
}



