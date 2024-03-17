package com.example.submission_dicoding_fundamental_awal.ui

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

class UserAdapter (var userList: List<ItemsItem>, private val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private var onItemClickListener: ((ItemsItem) -> Unit)? = null


    fun setOnItemClickListener(listener: (ItemsItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        onItemClickListener?.let { listener ->
            holder.itemView.setOnClickListener { listener(user) }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgList: ImageView = itemView.findViewById(R.id.imgList)
        private val tvUsername: TextView = itemView.findViewById(R.id.tv_username)

        fun bind(user: ItemsItem) {
            Glide.with(context).load(user.avatarUrl).circleCrop().into(imgList)
            tvUsername.text = user.login
        }
    }
}

