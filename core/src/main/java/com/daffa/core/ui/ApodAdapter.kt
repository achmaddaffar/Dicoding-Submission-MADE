package com.daffa.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daffa.core.R
import com.daffa.core.databinding.ItemListApodBinding
import com.daffa.core.domain.model.Apod

class ApodAdapter : RecyclerView.Adapter<ApodAdapter.ViewHolder>() {

    private var listData = ArrayList<Apod>()
    var onItemClick: ((Apod) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Apod>?) {
        if (newListData == null)
            return

        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_apod, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListApodBinding.bind(itemView)
        fun bind(data: Apod) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url)
                    .centerCrop()
                    .apply(RequestOptions().override(300, 200))
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemDate.text = data.date
                tvItemDescription.text = data.explanation
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}