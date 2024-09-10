package com.linker.tbook.view.component.my_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.findNavController
import com.linker.tbook.R
import com.linker.tbook.databinding.FragmentOrderAndPayBinding
import com.linker.tbook.view.base.BaseFragment
import com.linker.tbook.view.base.BaseViewModel

class OrderAndPayFragment : BaseFragment<FragmentOrderAndPayBinding> (
    FragmentOrderAndPayBinding::bind, R.layout.fragment_order_and_pay
) {

    // 선택한 결제 수단 저장 - 카카오페이 = 0, 일반 결제 = 1
    var payMethodNum = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로 가기 버튼 클릭
        binding.btnBack.setOnClickListener {
            goBack()
        }

        // 결제 수단 선택
        binding.choicePayMethod.setOnClickListener {
            onRadioButtonClicked(view)
        }

        // 결제하기 버튼 클릭
        binding.btnBuy.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_orderAndPayFragment_to_deliveryTrackingFragment)
        }
    }


    // 결제 수단 선택
    private fun onRadioButtonClicked(view: View) {
        if(view is RadioButton) {
            val checked = view.isChecked

            when(view.getId()) {
                R.id.kakao_pay ->
                    if(checked) {
                        showCustomToast("카카오페이가 실행됩니다.")
                        payMethodNum = 0
                    }
                R.id.genaral_pay ->
                    if(checked) {
                        showCustomToast("일반 결제가 선택되었습니다.")
                        payMethodNum = 1
                    }
            }
        }
    }
}