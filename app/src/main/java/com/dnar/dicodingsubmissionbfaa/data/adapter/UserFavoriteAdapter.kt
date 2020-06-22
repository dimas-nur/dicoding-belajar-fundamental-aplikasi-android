package com.dnar.dicodingsubmissionbfaa.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.databinding.ItemUserBinding

// UserFavorite Recycler View Adapter; Keyword : Adapter
class UserFavoriteAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<UserFavoriteAdapter.ViewHolder>() {

    private var list: List<UserEntity> = listOf()

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

        holder.binding.userEntity = data
        holder.itemView.setOnClickListener {
            listener.onUserClickListener(it, data)
        }
    }

    // Function : for change data in adapter
    fun setList(list: List<UserEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    // Interface : for listener onClick item
    interface Listener {
        fun onUserClickListener(view: View, data: UserEntity)
    }
}