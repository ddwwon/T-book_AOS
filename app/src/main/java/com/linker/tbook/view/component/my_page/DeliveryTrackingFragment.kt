package com.linker.tbook.view.component.my_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentDeliveryTrackingBinding
import com.linker.tbook.view.base.BaseFragment

class DeliveryTrackingFragment : BaseFragment<FragmentDeliveryTrackingBinding> (
    FragmentDeliveryTrackingBinding::bind, R.layout.fragment_delivery_tracking
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로 가기 버튼 클릭 시
        binding.btnBack.setOnClickListener {
            goBack()
        }
    }

}