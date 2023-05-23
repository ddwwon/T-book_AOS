package com.linker.tbook.view.component.home.homeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linker.tbook.R
import com.linker.tbook.databinding.ItemFilterOptionBinding

// 일반 모드 쇼핑 페이지 > 필터 Recyclerview Adapter
class FilterOptionAdapter(
    private val context: Context,
    val onClickOption: (position: Int) -> Unit
    ) :
    RecyclerView.Adapter<FilterOptionAdapter.FilterOptionViewHolder>() {

    var items = mutableListOf<FilterOptionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            FilterOptionViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_filter_option, parent, false)

        return FilterOptionViewHolder(ItemFilterOptionBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilterOptionViewHolder, position: Int) {
        holder.bind(items[position])

        // 필터 옵션 아이템 클릭 시
        holder.itemView.setOnClickListener {
            onClickOption(position)
        }
    }

    inner class FilterOptionViewHolder(private val binding: ItemFilterOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterOptionData) {
            // 필터 옵션 내용
            binding.textFilterOption.text = item.optionInfo

            // 옵션이 선택 되었을 경우
            if(item.selected) {
                binding.linearFilterOption.setBackgroundResource(R.drawable.green_solid_filter)
                binding.textFilterOption.setTextColor(context.getColor(R.color.white))
            }
            // 옵션이 선택 되지 않았을 경우
            else {
                binding.linearFilterOption.setBackgroundResource(R.drawable.gray_border_filter)
                binding.textFilterOption.setTextColor(context.getColor(R.color.black))
            }
        }
    }
}