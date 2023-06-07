package com.linker.tbook.view.component.product_detail.productOption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linker.tbook.R
import com.linker.tbook.databinding.BottomFragmentProductOptionBinding
import com.linker.tbook.view.component.product_detail.productDetailRecycler.ProductDetailData

// 색상, 갯수 선택 bottomSheet
class ProductOptionBottomSheet : BottomSheetDialogFragment() {
    // ViewBinding Setting
    lateinit var binding: BottomFragmentProductOptionBinding

    // 제품 색상 선택 RecyclerView
    private lateinit var productColorItems: MutableList<String>
    private lateinit var productColorAdapter: ProductColorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomFragmentProductOptionBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview 세팅
        initRecyclerView()

        // 샘플 데이터
        addProductColor("화이트")
        addProductColor("블랙")
        addProductColor("블랙")

        // 색상 선택지 열기/닫기
        binding.relativeSelectColor.setOnClickListener(View.OnClickListener {
            onClickOpenSelectColor()
        })

        // 갯수 감소
        binding.btnSubCount.setOnClickListener {
            var count = Integer.parseInt(binding.textProductCount.text.toString())
            if(count > 1) {
                count--
                binding.textProductCount.text = count.toString()
            }
            if(count == 1) {
                binding.btnSubCount.setColorFilter(resources.getColor(R.color.select_mode_gray))
            }
        }

        // 갯수 증가
        binding.btnAddCount.setOnClickListener {
            var count = Integer.parseInt(binding.textProductCount.text.toString())
            count++
            binding.textProductCount.text = count.toString()
            if(count == 2) {
                binding.btnSubCount.setColorFilter(resources.getColor(R.color.black))
            }
        }
    }

    // RecyclerView 세팅
    private fun initRecyclerView() {
        productColorAdapter = ProductColorAdapter(
            requireContext()
        )
        productColorItems = mutableListOf<String>()
        productColorAdapter.items = productColorItems
        binding.recyclerviewProductOption.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewProductOption.adapter = productColorAdapter
        binding.recyclerviewProductOption.isNestedScrollingEnabled = false
    }

    // 색상 선택지 추가
    private fun addProductColor(item: String) {
        productColorItems.add(item)
        productColorAdapter.notifyDataSetChanged()
    }

    // 색상 선택지 열기/닫기
    private fun onClickOpenSelectColor() {
        // 열기
        if(binding.recyclerviewProductOption.visibility == View.GONE) {
            binding.recyclerviewProductOption.visibility = View.VISIBLE
            binding.imageSelectBtn.setImageResource(R.drawable.ic_select_down)
        }
        // 닫기
        else {
            binding.recyclerviewProductOption.visibility = View.GONE
            binding.imageSelectBtn.setImageResource(R.drawable.ic_select_up)
        }
    }
}