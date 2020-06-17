package com.dnar.consumerapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnar.consumerapp.R
import com.dnar.consumerapp.data.model.UserDetail
import com.dnar.consumerapp.databinding.ItemUserBinding

// UserFavorite Recycler View Adapter; Keyword : Adapter
class UserFavoriteAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<UserFavoriteAdapter.ViewHolder>() {

    private var list: List<UserDetail> = listOf()

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

    // Function : for change data in adapter
    fun setList(list: List<UserDetail>) {
        this.list = list
        notifyDataSetChanged()
    }

    // Interface : for listener onClick item
    interface Listener {
        fun onUserClickListener(view: View, data: UserDetail)
    }
}