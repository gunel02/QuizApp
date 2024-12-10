package com.example.game.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.data_classes.user_info.UserData
import com.example.game.databinding.LayoutPersonRecycleBinding


class PersonRecycleAdapter(private val userList: List<UserData>) :
    RecyclerView.Adapter<PersonRecycleAdapter.PersonViewHolder>() {


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.apply {
            name.text = "Name : ${currentItem.name}"
            email.text = "Email : ${currentItem.email}"
            gender.text = "Gender : ${currentItem.gender}"
            status.text = "Status : ${currentItem.status}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding =
            LayoutPersonRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class PersonViewHolder(val binding: LayoutPersonRecycleBinding) :
        RecyclerView.ViewHolder(binding.root)

}