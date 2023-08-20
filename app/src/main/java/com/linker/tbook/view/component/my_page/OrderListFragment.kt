package com.linker.tbook.view.component.my_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentOrderListBinding
import com.linker.tbook.view.base.BaseFragment
import com.linker.tbook.view.component.my_page.myPageRecycler.orderAdapter
import com.linker.tbook.view.component.my_page.myPageRecycler.orderData

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(
    FragmentOrderListBinding::bind, R.layout.fragment_order_list
) {

    // 주문 목록에 담긴 주문 Recyclewview
    private lateinit var orderDatas: MutableList<orderData>
    private lateinit var orderAdater: orderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // recyclerview 세팅
        initRecycler()

        // 임시 데이터
        addOrder(orderData("data:image/png;base64,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",
            "아수스 젠북",
            "아수스",
            "금요일 출고",
            "1,500,000원"))
        addOrder(orderData("data:image/png;base64,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",
            "아수스 젠북",
            "아수스",
            "금요일 출고",
            "1,500,000원"))
        addOrder(orderData("data:image/png;base64,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",
            "아수스 젠북",
            "아수스",
            "금요일 출고",
            "1,500,000원"))

        // 뒤로 가기 클릭
        binding.btnBack.setOnClickListener {
            goBack()
        }

    }


    private fun initRecycler() {
        orderAdater = orderAdapter(
            requireContext(),
            onClick = {
                // 배송 조회 클릭
                Log.d("msg", "click")
                clickDeliveryTracking(it)
            }
        )
        orderDatas = mutableListOf<orderData>()
        orderAdater.datas = orderDatas
        binding.recyclerOrderListItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerOrderListItem.adapter = orderAdater
        binding.recyclerOrderListItem.isNestedScrollingEnabled = true
    }

    private fun addOrder(data: orderData) {
        orderDatas.add(data)
        orderAdater.notifyDataSetChanged()
    }

    // 배송 조회 클릭
    private fun clickDeliveryTracking(position: Int) {
        // 해당 아이템의 배송 조회 화면으로 이동
        Log.d("msg", "workrkwowkrwork")
        view?.findNavController()?.navigate(R.id.action_menu_myPage_to_deliveryTrackingFragment)
    }
}