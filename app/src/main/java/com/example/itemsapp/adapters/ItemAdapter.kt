package com.example.itemsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itemsapp.R
import com.example.itemsapp.databinding.ItemPreviewBinding
import com.example.itemsapp.model.ApiResponse
import com.example.itemsapp.model.ApiResponseItem
import kotlinx.android.synthetic.main.item_preview.view.*

class ItemAdapter : PagingDataAdapter<ApiResponseItem, ItemAdapter.ItemViewHolder>(ITEM_COMPARATOR){
    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null)
        {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
     val binding =
         ItemPreviewBinding.inflate(LayoutInflater.from(parent.context),
         parent,
         false)
            return ItemViewHolder(binding)
    }
    class ItemViewHolder(private val binding: ItemPreviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ApiResponseItem){
            binding.apply {
                tvItemName.text = item.name
                tvRemainingstock.setText("${item.availableCount} ${item.priceUnit}")
                tvSellingprice.setText("₹${item.sellingPrice}")
                tvStockvalue.setText("₹${item.mrp}")
            }
        }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<ApiResponseItem>() {
            override fun areItemsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem) =
                oldItem.stockItemId== newItem.stockItemId

            override fun areContentsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem) =
                oldItem == newItem
        }
    }

}