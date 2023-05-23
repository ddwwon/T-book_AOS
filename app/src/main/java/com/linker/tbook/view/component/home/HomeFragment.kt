package com.linker.tbook.view.component.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentHomeBinding
import com.linker.tbook.view.base.BaseFragment
import com.linker.tbook.view.component.home.homeRecycler.FilterOptionAdapter
import com.linker.tbook.view.component.home.homeRecycler.FilterOptionData
import com.linker.tbook.view.component.home.homeRecycler.ProductListAdapter
import com.linker.tbook.view.component.home.homeRecycler.ProductListData

class HomeFragment : BaseFragment<FragmentHomeBinding> (
    FragmentHomeBinding::bind, R.layout.fragment_home
    ){

    // 필터 Recyclerview
    // 제조사
    private lateinit var filterCompanyItems: MutableList<FilterOptionData>
    private lateinit var filterCompanyAdapter: FilterOptionAdapter
    // 용도
    private lateinit var filterPurposeItems: MutableList<FilterOptionData>
    private lateinit var filterPurposeAdapter: FilterOptionAdapter
    // 무게
    private lateinit var filterWeightItems: MutableList<FilterOptionData>
    private lateinit var filterWeightAdapter: FilterOptionAdapter
    // 가격대
    private lateinit var filterPriceItems: MutableList<FilterOptionData>
    private lateinit var filterPriceAdapter: FilterOptionAdapter

    // 제품 리스트 RecyclerView
    private lateinit var productListItems: MutableList<ProductListData>
    private lateinit var productListAdapter: ProductListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview 세팅
        initRecycler()

        // 임시 데이터 - 제조사
        addCompanyOption(FilterOptionData("SAMSUNG", false))
        addCompanyOption(FilterOptionData("LG", false))
        addCompanyOption(FilterOptionData("RAZER", false))
        addCompanyOption(FilterOptionData("ASUS", false))
        addCompanyOption(FilterOptionData("MSI", false))
        addCompanyOption(FilterOptionData("레노버", false))

        // 임시 데이터 - 용도
        addPurposeOption(FilterOptionData("고사양 게임 🎮", false))
        addPurposeOption(FilterOptionData("문서 작업 📑", false))
        addPurposeOption(FilterOptionData("그래픽 작업 🖼️", false))
        addPurposeOption(FilterOptionData("가성비 최고 👍🏻", false))
        addPurposeOption(FilterOptionData("트부기 추천 😀", false))

        // 임시 데이터 - 무게
        addWeightOption(FilterOptionData("~1kg", false))
        addWeightOption(FilterOptionData("~1.5kg", false))
        addWeightOption(FilterOptionData("~2kg", false))
        addWeightOption(FilterOptionData("~3kg", false))

        // 임시 데이터 - 가격대
        addPriceOption(FilterOptionData("~40만 원", false))
        addPriceOption(FilterOptionData("40~70만 원", false))
        addPriceOption(FilterOptionData("70~100만 원", false))
        addPriceOption(FilterOptionData("100~200만 원", false))
        addPriceOption(FilterOptionData("200만 원 이상", false))

        // 임시 데이타 - 제품
        addProductItem(ProductListData("https://github.com/ddwwon/T-book_AOS/assets/76805879/64169f61-b536-4535-96b1-c481a6c40cbc",
            "DELL", "XPS 15"))
        addProductItem(ProductListData("https://github.com/ddwwon/T-book_AOS/assets/76805879/1b7139a8-ce0f-40fa-8c2a-30581f25bdeb",
            "ASUS", "ROG 제퍼러스"))
        addProductItem(ProductListData("https://github.com/ddwwon/T-book_AOS/assets/76805879/64169f61-b536-4535-96b1-c481a6c40cbc",
            "DELL", "XPS 15"))

        // 필터 옵션 선택창 열고 닫기 버튼
        binding.btnFilter.setOnClickListener { clickFilterOptionBtn() }
    }

    // recyclerview 세팅
    private fun initRecycler() {
        // 필터 - 제조사
        filterCompanyAdapter = FilterOptionAdapter(
            requireContext(),
            onClickOption = {
                selectCompanyOption(it)
            }
        )
        filterCompanyItems = mutableListOf<FilterOptionData>()
        filterCompanyAdapter.items = filterCompanyItems
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP // 다음 아이템의 크기가 남은 여유공간보다 큰 경우 자동으로 줄바꿈
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START // 좌측 정렬
        }.let {
            binding.recyclerviewFilterOfCompany.layoutManager = it
            binding.recyclerviewFilterOfCompany.adapter = filterCompanyAdapter
        }

        // 필터 - 용도
        filterPurposeAdapter = FilterOptionAdapter(
            requireContext(),
            onClickOption = {
                selectPurposeOption(it)
            }
        )
        filterPurposeItems = mutableListOf<FilterOptionData>()
        filterPurposeAdapter.items = filterPurposeItems
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP // 다음 아이템의 크기가 남은 여유공간보다 큰 경우 자동으로 줄바꿈
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START // 좌측 정렬
        }.let {
            binding.recyclerviewFilterOfPurpose.layoutManager = it
            binding.recyclerviewFilterOfPurpose.adapter = filterPurposeAdapter
        }

        // 필터 - 무게
        filterWeightAdapter = FilterOptionAdapter(
            requireContext(),
            onClickOption = {
                selectWeightOption(it)
            }
        )
        filterWeightItems = mutableListOf<FilterOptionData>()
        filterWeightAdapter.items = filterWeightItems
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP // 다음 아이템의 크기가 남은 여유공간보다 큰 경우 자동으로 줄바꿈
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START // 좌측 정렬
        }.let {
            binding.recyclerviewFilterOfWeight.layoutManager = it
            binding.recyclerviewFilterOfWeight.adapter = filterWeightAdapter
        }

        // 필터 - 가격대
        filterPriceAdapter = FilterOptionAdapter(
            requireContext(),
            onClickOption = {
                selectPriceOption(it)
            }
        )
        filterPriceItems = mutableListOf<FilterOptionData>()
        filterPriceAdapter.items = filterPriceItems
        FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP // 다음 아이템의 크기가 남은 여유공간보다 큰 경우 자동으로 줄바꿈
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START // 좌측 정렬
        }.let {
            binding.recyclerviewFilterOfPrice.layoutManager = it
            binding.recyclerviewFilterOfPrice.adapter = filterPriceAdapter
        }

        // 제품 리스트
        productListAdapter = ProductListAdapter(
            requireContext(),
            onClickProduct = {
                clickProductItem(it)
            }
        )
        productListItems = mutableListOf<ProductListData>()
        productListAdapter.items = productListItems
        binding.recyclerviewProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewProductList.adapter = productListAdapter
        binding.recyclerviewProductList.isNestedScrollingEnabled = true
    }

    // 필터 옵션 추가 - 제조사
    private fun addCompanyOption(item: FilterOptionData) {
        filterCompanyItems.add(item)
        filterCompanyAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 추가 - 용도
    private fun addPurposeOption(item: FilterOptionData) {
        filterPurposeItems.add(item)
        filterPurposeAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 추가 - 무게
    private fun addWeightOption(item: FilterOptionData) {
        filterWeightItems.add(item)
        filterWeightAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 추가 - 가격대
    private fun addPriceOption(item: FilterOptionData) {
        filterPriceItems.add(item)
        filterPriceAdapter.notifyDataSetChanged()
    }

    // 제품 추가
    private fun addProductItem(item: ProductListData) {
        productListItems.add(item)
        productListAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 선택 시 - 제조사
    private fun selectCompanyOption(position: Int) {
        // selected 값 true/false 전환
        filterCompanyItems[position] = FilterOptionData(
            filterCompanyItems[position].optionInfo,
            !filterCompanyItems[position].selected
        )
        filterCompanyAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 선택 시 - 용도
    private fun selectPurposeOption(position: Int) {
        // selected 값 true/false 전환
        filterPurposeItems[position] = FilterOptionData(
            filterPurposeItems[position].optionInfo,
            !filterPurposeItems[position].selected
        )
        filterPurposeAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 선택 시 - 무게
    private fun selectWeightOption(position: Int) {
        // selected 값 true/false 전환
        filterWeightItems[position] = FilterOptionData(
            filterWeightItems[position].optionInfo,
            !filterWeightItems[position].selected
        )
        filterWeightAdapter.notifyDataSetChanged()
    }

    // 필터 옵션 선택 시 - 가격대
    private fun selectPriceOption(position: Int) {
        // selected 값 true/false 전환
        filterPriceItems[position] = FilterOptionData(
            filterPriceItems[position].optionInfo,
            !filterPriceItems[position].selected
        )
        filterPriceAdapter.notifyDataSetChanged()
    }

    // 필터 선택 옵션 열고 닫기 버튼
    private fun clickFilterOptionBtn() {
        // 필터 선택 옵션창이 닫혀있는 경우 -> 열기
        if(binding.linearOpenFilter.visibility == View.GONE) {
            binding.linearOpenFilter.visibility = View.VISIBLE
        }
        // 필터 선택 옵션창이 열려있는 경우 -> 닫기
        else {
            binding.linearOpenFilter.visibility = View.GONE
        }
    }

    // 제품 아이템 선택
    private fun clickProductItem(position: Int) {
        // 제품 상세 페이지로 이동
        view?.findNavController()?.navigate(R.id.action_home_to_product_detail)
        // 이동할 때 선택 정보 전달
    }
}