package com.linker.tbook.view.component.my_page.myPageRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linker.tbook.R
import com.linker.tbook.databinding.DeliveryListItemBinding

class deliverAdapter(
    private val context: Context
    ): RecyclerView.Adapter<deliverAdapter.deliverViewHolder>()
    {
        var datas = mutableListOf<deliverData>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): deliverAdapter.deliverViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.delivery_list_item, parent, false)
            return deliverViewHolder(DeliveryListItemBinding.bind(view))
        }

        override fun onBindViewHolder(holder: deliverAdapter.deliverViewHolder, position: Int) {
            holder.bind(datas[position])
        }

        override fun getItemCount(): Int = datas.size

        inner class deliverViewHolder(private val binding: DeliveryListItemBinding):
                RecyclerView.ViewHolder(binding.root) {
                    fun bind(data: deliverData) {
                        // 상품 이미지
                        Glide.with(context)
                            .load(data.image)
                            .placeholder(R.drawable.notebook)
                            .into(binding.productImage)
                        // 상품명
                        binding.textProductName.text = data.name
                        // 운송장 번호
                        binding.textInvoiceNum.text = data.invoieNumber.toString()
                        //  배송 주소
                        binding.textDeliveryAddress.text = data.address
                        // 배송원
                        binding.textDeliveryWorker.text = data.deliveryWorker
                        // 결제 금액
                        binding.textPayAmount.text = data.payAmount
                    }
                }
    }