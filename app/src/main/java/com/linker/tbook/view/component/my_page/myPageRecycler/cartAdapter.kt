package com.linker.tbook.view.component.my_page.myPageRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linker.tbook.R
import com.linker.tbook.databinding.CartItemBinding
import com.linker.tbook.databinding.FragmentCartBinding

class cartAdapter(
    private val context: Context
    ):
    RecyclerView.Adapter<cartAdapter.cartViewHolder>()
    {

        var datas = mutableListOf<cartData>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): cartAdapter.cartViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
            return cartViewHolder(CartItemBinding.bind(view))
        }

        override fun onBindViewHolder(holder: cartAdapter.cartViewHolder, position: Int) {
            holder.bind(datas[position])
        }

        override fun getItemCount(): Int  = datas.size

        inner class cartViewHolder(private val binding: CartItemBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    fun bind(data: cartData) {
                        // 상품 이미지
                        Glide.with(context)
                            .load(data.image)
                            .placeholder(R.drawable.notebook)
                            .into(binding.imageProduct)
                        // 상품명
                        binding.textProductName.text = data.name
                        // 제조사
                        binding.textProductName.text = data.production
                        // 예상 출고일
                        binding.textDeliveryDate.text = data.scheduledToShip
                        // 상품 가격
                        binding.textProductPrice.text = data.productPrice
                        // 상품 개수
                        binding.productCount.text = data.productCount
                    }
                }
    }