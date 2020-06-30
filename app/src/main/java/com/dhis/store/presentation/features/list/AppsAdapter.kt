package com.dhis.store.presentation.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dhis.store.core.entity.DhisApp
import com.dhis.store.databinding.ItemAppBinding

class AppsAdapter(
    private val clickListener: (Int) -> Unit
) : ListAdapter<DhisApp, RecyclerView.ViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppViewHolder(
            ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val app = getItem(position)
        if (holder is AppViewHolder) holder.bind(app)
    }

    class AppViewHolder(
        private val binding: ItemAppBinding,
        private val clickListener: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { binding.app?.let { clickListener(it.id) } }
        }

        fun bind(item: DhisApp) {
            binding.apply {
                app = item
                executePendingBindings()
            }
        }
    }
}

private class AppDiffCallback : DiffUtil.ItemCallback<DhisApp>() {

    override fun areItemsTheSame(oldItem: DhisApp, newItem: DhisApp): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DhisApp, newItem: DhisApp): Boolean =
        oldItem == newItem
}
