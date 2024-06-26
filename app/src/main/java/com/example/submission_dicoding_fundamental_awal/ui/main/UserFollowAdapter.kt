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
import com.example.submission_dicoding_fundamental_awal.data.response.followItems

class UserFollowAdapter (private val userList: List<followItems>, private val context: Context) :
    RecyclerView.Adapter<UserFollowAdapter.UserFollowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false)
        return UserFollowViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserFollowViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    inner class UserFollowViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val img : ImageView = view.findViewById(R.id.imgListFollow)
        private val tvUsername : TextView = view.findViewById(R.id.tv_usernameFollow)

        fun bind(user : followItems) {
            Glide.with(context).load(user.avatarUrl).circleCrop().into(img)

            tvUsername.text = user.login
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}