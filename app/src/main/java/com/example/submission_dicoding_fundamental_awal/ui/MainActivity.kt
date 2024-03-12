package com.example.submission_dicoding_fundamental_awal.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel.listUser.observe(this) { listuser ->
            setListData(listuser)
        }

        mainViewModel.loading.observe(this) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.hint = searchView.text
                searchView.hide()
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    private fun setListData(consumerList: List<ItemsItem>){
        val adapter = UserAdapter(consumerList,this)
        binding.rvUser.adapter = adapter
        adapter.notifyDataSetChanged()

    }
    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility =View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}