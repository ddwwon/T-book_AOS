package com.linker.tbook.view.component.product_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentProductDetailBinding
import com.linker.tbook.view.base.BaseFragment

// 제품 상세
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(
    FragmentProductDetailBinding::bind, R.layout.fragment_product_detail
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼 선택
        binding.btnBack.setOnClickListener { backToHome() }
    }

    // 뒤로가기
    private fun backToHome() {
        view?.findNavController()?.popBackStack()
    }

}