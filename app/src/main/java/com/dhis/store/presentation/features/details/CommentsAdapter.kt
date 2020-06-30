package com.dhis.store.presentation.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dhis.store.core.entity.AppComment
import com.dhis.store.databinding.ItemCommentBinding

class CommentsAdapter :
    ListAdapter<AppComment, RecyclerView.ViewHolder>(AppCommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppCommentViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comment = getItem(position)
        if (holder is AppCommentViewHolder) holder.bind(comment)
    }

    class AppCommentViewHolder(
        private val binding: ItemCommentBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppComment) {
            binding.apply {
                comment = item
                executePendingBindings()
            }
        }
    }
}

private class AppCommentDiffCallback : DiffUtil.ItemCallback<AppComment>() {

    override fun areItemsTheSame(oldItem: AppComment, newItem: AppComment): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: AppComment, newItem: AppComment): Boolean =
        oldItem == newItem
}
