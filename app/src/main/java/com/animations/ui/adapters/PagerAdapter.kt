package com.animations.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.animations.databinding.ItemPagerBinding
import com.bumptech.glide.Glide

class PagerAdapter(var list: ArrayList<String>,val onItemClick:(Pair<View,String>)->Unit) :
    RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {

     fun  updateData(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PagerViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            Glide.with(binding.root.context).load(url).into(binding.imageView)
            binding.imageView.setOnClickListener {
                onItemClick(Pair(binding.imageView,url))
            }
        }
    }
}