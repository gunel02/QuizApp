package com.example.game.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.game.data_classes.user_info.UserInfoData
import com.example.game.databinding.LayoutUserInfoRecycleBinding

class UserInfoRecycleAdapter(val context: Context?, private val infoList: List<UserInfoData>) :
    RecyclerView.Adapter<UserInfoRecycleAdapter.UserInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        val binding =
            LayoutUserInfoRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserInfoViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        val currentInfo = infoList[position]
        holder.binding.apply {
            firstName.text = "First Name: ${currentInfo.firstName}"
            lastName.text = "Last Name: ${currentInfo.lastName}"
            email.text = "Email: ${currentInfo.email}"

            context?.let { notNullContext ->
                Glide.with(notNullContext)
                    .load(currentInfo.avatar)
                    .into(holder.binding.imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    class UserInfoViewHolder(val binding: LayoutUserInfoRecycleBinding) :
        RecyclerView.ViewHolder(binding.root)


}