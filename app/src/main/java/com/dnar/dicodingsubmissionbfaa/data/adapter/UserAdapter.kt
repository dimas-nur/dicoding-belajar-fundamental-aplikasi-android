package com.dnar.dicodingsubmissionbfaa.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.model.User
import com.dnar.dicodingsubmissionbfaa.databinding.ItemUserBinding

// User Recycler View Adapter; Keyword : Adapter
class UserAdapter(private val listener: Listener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var list: List<User> = listOf()

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
        val data = list[position]

        holder.binding.user = data
        holder.itemView.setOnClickListener {
            listener.onUserClickListener(it, data)
        }
    }

    fun setList(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface Listener {
        fun onUserClickListener(view: View, data: User)
    }
}