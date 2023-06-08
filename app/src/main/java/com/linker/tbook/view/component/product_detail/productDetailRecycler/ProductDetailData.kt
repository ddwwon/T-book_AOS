package com.linker.tbook.view.component.product_detail.productDetailRecycler

// 제품 상세 정보: CPU, 램, 저장용량, 해상도, 그래픽, 배터리, 운영체제, 무게
data class ProductDetailData(
    val productDetailLabel: String, // 제품 상세 라벨
    val productDetail: String,      // 제품 상세 내용
    val productDetailInfo: String,  // 제품 상세 설명
)
