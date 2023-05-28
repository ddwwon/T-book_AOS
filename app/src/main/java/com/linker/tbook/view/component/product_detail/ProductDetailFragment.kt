package com.linker.tbook.view.component.product_detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentProductDetailBinding
import com.linker.tbook.utils.ImageLoader
import com.linker.tbook.view.base.BaseFragment
import com.linker.tbook.view.component.product_detail.productDetailRecycler.ProductDetailAdapter
import com.linker.tbook.view.component.product_detail.productDetailRecycler.ProductDetailData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// 제품 상세
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(
    FragmentProductDetailBinding::bind, R.layout.fragment_product_detail
) {
    // 제품 상세 RecyclerView
    private lateinit var productDetailItems: MutableList<ProductDetailData>
    private lateinit var productDetailAdapter: ProductDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Recyclerview 세팅
        initRecyclerView()

        // 뒤로가기 버튼 선택
        binding.btnBack.setOnClickListener { backToHome() }

        // 제품 이미지 로드
        // 샘플 이미지
        setProductImage("https://github.com/ddwwon/T-book_AOS/assets/76805879/1b7139a8-ce0f-40fa-8c2a-30581f25bdeb")

        // 제품 정보 삽입

        // 제품 상세 정보
        // 샘플 데이터
        addProductDetail(ProductDetailData("CPU", "코어i5-1235U (1.3GHz)", ""))
        addProductDetail(ProductDetailData("램", "LPDDR4X 16gb 4266MHz", ""))
        addProductDetail(ProductDetailData("저장용량", "500GB", ""))
        addProductDetail(ProductDetailData("해상도", "1920 X 1080(FHD) 250nit", ""))
        addProductDetail(ProductDetailData("그래픽", "내장그래픽(Iris XE)", ""))
        addProductDetail(ProductDetailData("배터리", "80wh", ""))
        addProductDetail(ProductDetailData("운영체제", "FreeDOS", ""))
        addProductDetail(ProductDetailData("무게","1.14kg", ""))

        // 제품 가격 정보

        // 장바구니, 구매하기 버튼
    }

    // RecyclerView 세팅
    private fun initRecyclerView() {
        productDetailAdapter = ProductDetailAdapter(
            requireContext()
        )
        productDetailItems = mutableListOf<ProductDetailData>()
        productDetailAdapter.items = productDetailItems
        binding.recyclerviewProductDetail.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewProductDetail.adapter = productDetailAdapter
        binding.recyclerviewProductDetail.isNestedScrollingEnabled = false
    }

    // 뒤로가기
    private fun backToHome() {
        view?.findNavController()?.popBackStack()
    }

    // 제품 이미지 세팅
    private fun setProductImage(imageUrl: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val productImageBitmap: Bitmap? = withContext(Dispatchers.IO) {
                ImageLoader.loadImage(imageUrl!!)
            }
            binding.imageProductInfo.setImageBitmap(productImageBitmap)
        }
    }

    // 제품 정보 세팅

    // 제품 상세 정보 추가
    private fun addProductDetail(item: ProductDetailData) {
        productDetailItems.add(item)
        productDetailAdapter.notifyDataSetChanged()
    }

    // 제품 가격 정보 세팅

    // 장바구니, 구매하기 bottomSheet

}