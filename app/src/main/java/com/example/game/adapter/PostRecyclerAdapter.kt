package com.example.game.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.data_classes.user_info.post.UserResponse
import com.example.game.databinding.LayoutPostRecycleBinding

class PostRecyclerAdapter(private val postList: List<UserResponse>) :
    RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutPostRecycleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutPostRecycleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = postList[position]
        holder.binding.apply {
            name.text ="Name: ${current.name}"
            job.text = "Job: ${current.job}"
            id.text = "Id: ${current.id}"
            createdAt.text = "Created At: ${current.createdAt}"
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}