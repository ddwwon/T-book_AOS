package com.linker.tbook.view.component.home.homeRecycler

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linker.tbook.R
import com.linker.tbook.databinding.ItemProductBinding
import com.linker.tbook.utils.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// 일반 모드 쇼핑 페이지 > 제품 리스트 Recyclerview Adapter
class ProductListAdapter(
    private val context: Context,
    val onClickProduct: (position: Int) -> Unit
    ) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    var items = mutableListOf<ProductListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ProductListViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)

        return ProductListViewHolder(ItemProductBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(items[position])

        // 제품 리스트에서 아이템 클릭 시
        holder.itemView.setOnClickListener {
            onClickProduct(position)
        }
    }

    inner class ProductListViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductListData) {
            // 제품 이미지
            CoroutineScope(Dispatchers.Main).launch {
                val productImageBitmap: Bitmap? = withContext(Dispatchers.IO) {
                    ImageLoader.loadImage(item.productImageUri)
                }
                binding.imageProduct.setImageBitmap(productImageBitmap)
            }

            // 제품 제조사
            val productCompanyText = "[" + item.productCompany + "]"
            binding.textProductCompany.text = productCompanyText

            // 제품명
            binding.textProductName.text = item.productName
        }
    }
}