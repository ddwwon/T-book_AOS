package com.linker.tbook.view.component.product_detail.productDetailRecycler

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linker.tbook.R
import com.linker.tbook.databinding.ItemProductDetailBinding
import java.util.Timer

// 제품 상세 정보: CPU, 램, 저장용량, 해상도, 그래픽, 배터리, 운영체제, 무게
class ProductDetailAdapter (
    private val context: Context,
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
        holder.bind(items[position], position)
    }

    inner class ProductDetailViewHolder(private val binding: ItemProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductDetailData, position: Int) {
            // 첫 번째 설명인 경우
            if(position == 0) {
                binding.relativeProductDetail.setPadding(0, 60, 0, 0)
            }
            binding.textProductLabel.text = item.productDetailLabel // 제품 정보 라벨
            binding.textProductDetail.text = item.productDetail    // 제품 정보 내용

            // 제품 정보 설명
            binding.textDetailInfo.text = item.productDetailInfo

            // 단어 설명 버튼
            binding.btnViewInfo.setOnClickListener {
                binding.frameDetailInfo.visibility = View.VISIBLE
                // 2초만 보여주고 닫기
                Handler().postDelayed(Runnable {
                    binding.frameDetailInfo.visibility = View.INVISIBLE
                }, 2000)
            }
        }
    }
}