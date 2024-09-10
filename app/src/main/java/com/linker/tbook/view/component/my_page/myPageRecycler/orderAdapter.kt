package com.linker.tbook.view.component.my_page.myPageRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linker.tbook.R
import com.linker.tbook.databinding.OrderListItemBinding

class orderAdapter(
    private val context: Context,
    val onClick: (position:Int) -> Unit
    ): RecyclerView.Adapter<orderAdapter.orderListViewHolder>() {

        var datas = mutableListOf<orderData>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): orderAdapter.orderListViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false)

            return orderListViewHolder(OrderListItemBinding.bind(view))
        }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: orderAdapter.orderListViewHolder, position: Int) {
        holder.bind(datas[position])

        // 배송 조회 클릭 시
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

        inner class orderListViewHolder(private val binding: OrderListItemBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: orderData) {
                    // 상품 이미지
                    Glide.with(context)
                        .load(data.image)
                        .placeholder(R.drawable.notebook)
                        .into(binding.imageProduct)
                    // 상품명
                    binding.textProductName.text = data.name
                    // 제조사
                    binding.textManufacturing.text = data.production
                    // 예상 출고일
                    binding.textDeliveryDate.text = data.scheduledToShip
                    // 상품 가격
                    binding.textProductPrice.text = data.productPrice
                }
            }
}