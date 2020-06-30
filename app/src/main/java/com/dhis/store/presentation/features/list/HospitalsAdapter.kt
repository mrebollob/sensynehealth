package com.dhis.store.presentation.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dhis.store.core.entity.Hospital
import com.dhis.store.databinding.ItemHospitalBinding

class HospitalsAdapter(
    private val clickListener: (Int) -> Unit
) : ListAdapter<Hospital, RecyclerView.ViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppViewHolder(
            ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val app = getItem(position)
        if (holder is AppViewHolder) holder.bind(app)
    }

    class AppViewHolder(
        private val binding: ItemHospitalBinding,
        private val clickListener: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { binding.app?.let { clickListener(it.id) } }
        }

        fun bind(item: Hospital) {
            binding.apply {
                app = item
                executePendingBindings()
            }
        }
    }
}

private class AppDiffCallback : DiffUtil.ItemCallback<Hospital>() {

    override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean =
        oldItem == newItem
}
