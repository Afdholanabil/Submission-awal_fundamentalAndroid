package com.example.submission_dicoding_fundamental_awal.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission_dicoding_fundamental_awal.R
import com.example.submission_dicoding_fundamental_awal.data.response.ItemsItem
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser

class FavUserAdapter(var userFavList: List<FavoriteUser>, private val context: Context) : RecyclerView.Adapter<FavUserAdapter.UserViewHolder>() {


    private var onItemClickListener : ((FavoriteUser) -> Unit)? = null


    fun setOnClickListener(listener: (FavoriteUser) -> Unit) {
        onItemClickListener = listener
    }
    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val img : ImageView = itemView.findViewById(R.id.iv_favUser)
        private val tv : TextView = itemView.findViewById(R.id.tv_favUsername)
        fun bind(user: FavoriteUser) {
            Glide.with(context).load(user.avatarUrl).circleCrop().into(img)
            tv.text = user.username

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userFavList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userFavList[position]
        holder.bind(user)

        onItemClickListener?.let { listener ->
            holder.itemView.setOnClickListener{ listener(user)}
        }
    }


}