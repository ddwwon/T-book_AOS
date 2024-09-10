package com.linker.tbook.view.component.home.homeRecycler

// 일반 모드 쇼핑 페이지 > 필터 Recyclerview item
data class FilterOptionData(
    val optionInfo: String, // 옵션 내용
    var selected: Boolean   // 선택 여부
)
