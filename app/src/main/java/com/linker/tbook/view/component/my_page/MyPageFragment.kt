package com.linker.tbook.view.component.my_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentMyPageBinding
import com.linker.tbook.view.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(
    FragmentMyPageBinding::bind, R.layout.fragment_my_page
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 주문 목록 클릭 시
        binding.btnOrderList.setOnClickListener {
            clickOrderList()
        }
    }

    private fun clickOrderList() {
        // 주문 목록 화면으로 이동
        view?.findNavController()?.navigate(R.id.action_menu_myPage_to_orderListFragment)
    }
}