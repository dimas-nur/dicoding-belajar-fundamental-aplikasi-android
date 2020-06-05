package com.dnar.dicodingsubmissionbfaa.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.databinding.ItemUserBinding

// UserFollow Recycler View Adapter; Keyword : Adapter
class UserFollowAdapter : RecyclerView.Adapter<UserFollowAdapter.ViewHolder>() {

    private var list: List<UserSearch> = listOf()

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.user = list[position]
    }

    fun setList(list: List<UserSearch>) {
        this.list = list
        notifyDataSetChanged()
    }
}