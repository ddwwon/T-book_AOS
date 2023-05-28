package com.linker.tbook.view.component.product_detail.productDetailRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linker.tbook.R
import com.linker.tbook.databinding.ItemProductDetailBinding

// 제품 상세 정보: CPU, 램, 저장용량, 해상도, 그래픽, 배터리, 운영체제, 무게
class ProductDetailAdapter (
    private val context: Context,
    // val onClickInfo: (position: Int) -> Unit // 제품 정보 설명 버튼 클릭 시
) :
    RecyclerView.Adapter<ProductDetailAdapter.ProductDetailViewHolder>() {

    var items = mutableListOf<ProductDetailData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ProductDetailViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_product_detail, parent, false)

        return ProductDetailViewHolder(ItemProductDetailBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductDetailViewHolder, position: Int) {
        holder.bind(items[position])

        // 제품 상세 설명 버튼 클릭 시
        /*holder.itemView.setOnClickListener {
            onClickProduct(position)
        }*/
    }

    inner class ProductDetailViewHolder(private val binding: ItemProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductDetailData) {
            binding.textProductLabel.text = item.productDetailLabel // 제품 정보 라벨
            binding.textProductDetail.text = item.productDetail    // 제품 정보 내용

            // 제품 정보 설명
        }
    }
}