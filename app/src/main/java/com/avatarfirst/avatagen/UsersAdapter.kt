package com.avatarfirst.avatagen

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatagen.apis.GithubUserApiResponseItem
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import kotlinx.android.synthetic.main.user_layout.view.*

class UsersAdapter(
    var context: Context,
    var users: List<GithubUserApiResponseItem>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.usename.text = users[position].login
        holder.itemView.userAvatar.setImageDrawable(
            AvatarGenerator.AvatarBuilder(context)
                .setLabel(users[position].login)
                .setAvatarSize(120)
                .setTextSize(20)
                .toCircle()
                .toLowerCase()
                .build()
        )
    }

}