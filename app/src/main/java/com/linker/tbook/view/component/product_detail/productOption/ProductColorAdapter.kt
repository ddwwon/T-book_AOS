package com.linker.tbook.view.component.product_detail.productOption

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linker.tbook.R
import com.linker.tbook.databinding.ItemSelectColorBinding

class ProductColorAdapter(
    private val context: Context,
    ) :
    RecyclerView.Adapter<ProductColorAdapter.ProductColorViewHolder>() {

        var items = mutableListOf<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
                ProductColorViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.item_select_color, parent, false)

            return ProductColorViewHolder(ItemSelectColorBinding.bind(view))
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ProductColorViewHolder, position: Int) {
            holder.bind(items[position], position)
        }

        inner class ProductColorViewHolder(private val binding: ItemSelectColorBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: String, position: Int) {
                binding.textColorOption.text = item
            }
        }
    }