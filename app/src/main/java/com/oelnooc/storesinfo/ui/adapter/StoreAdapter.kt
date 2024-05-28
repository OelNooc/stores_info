package com.oelnooc.storesinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oelnooc.storesinfo.data.model.Store
import com.oelnooc.storesinfo.databinding.StoreLayoutBinding

class StoreAdapter (private val stores: MutableList<Store>) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    inner class StoreViewHolder(private val binding: StoreLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            binding.nameTv.text = store.attributes.name
            binding.codeTv.text = store.attributes.code
            binding.directionTv.text = store.attributes.fullAddress
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = StoreLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(stores[position])
    }

    override fun getItemCount(): Int = stores.size

    fun addStores(newStores: List<Store>) {
        val previousSize = stores.size
        stores.addAll(newStores)
        notifyItemRangeInserted(previousSize, newStores.size)
    }
}