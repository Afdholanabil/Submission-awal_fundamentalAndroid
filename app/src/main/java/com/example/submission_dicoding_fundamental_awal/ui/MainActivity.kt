package com.example.submission_dicoding_fundamental_awal.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        mainViewModel.listUser.observe(this) {
                listuser ->
            setListData(listuser)
            Log.d("MainActivity", "Data main : $listuser")
        }


        mainViewModel.loading.observe(this) {
            showLoading(it)
        }

        mainViewModel.snackbar.observe(this) {
            it.getContentIfNotHandled()?.let { snackBar ->
                Snackbar.make(window.decorView.rootView, snackBar, Snackbar.LENGTH_SHORT).show()
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding?.rvUser?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding?.rvUser?.addItemDecoration(itemDecoration)

        binding?.let { safeBinding ->
            with(safeBinding) {
                searchView.setupWithSearchBar(searchBar)
                searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                    val searchString = searchView.text.toString().trim()

                    if (searchString.isNotEmpty()) {
                        searchView.hide()
                        mainViewModel.userSearch(searchString)
                    } else {
                        Log.e(
                            "searchView.editText",
                            "Onfailure : Value kosong dan isi searchBar : $searchString"

                        )
                        mainViewModel.searchBarKosong()
                    }
                    false
                }
            }
        }

        val adapter = UserAdapter(emptyList(),this)

        binding?.rvUser?.adapter = adapter

        adapter.setOnItemClickListener { selectedList ->
            val intent = Intent(this, DetailUserActivity::class.java)
            intent.putExtra("login" , selectedList.login)
            startActivity(intent)
        }

    }

    private fun setListData(consumerList: List<ItemsItem>) {
        (binding?.rvUser?.adapter as? UserAdapter)?.apply {
            userList = consumerList
            notifyDataSetChanged()
        }
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility =View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }

}