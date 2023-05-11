package com.linker.tbook.view.component.select_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linker.tbook.MainActivity
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivitySelectModeBinding

// 사용방식 선택 - AI 추천 모드, 일반 모드
class SelectModeActivity : AppCompatActivity() {

    // ViewBinding Setting
    private var _binding: ActivitySelectModeBinding? = null
    private val binding get() = _binding!!

    // AI 모드 선택했는지 유무
    private var selecAIMode: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySelectModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이전에 선택한 값이 저장되어 있는 경우 적용해주기
        // selectMode =
        // 일단 임의로 AI 값 저장해둠
        selecAIMode = true

        // 뒤로가기 버튼 > 회원가입 페이지 닫기
        binding.btnBack.setOnClickListener {
            finish()
        }

        // AI 모드 선택
        binding.btnRecommendAi.setOnClickListener {
            selecAIMode = true

            // AI 모드 버튼 활성화, 일반 모드 버튼 비활성화
            binding.btnRecommendAi.setBackgroundResource(R.drawable.green_solid_button)
            binding.btnRecommendAi.setTextColor(getResources().getColor(R.color.white))
            binding.btnRecommendNormal.setBackgroundResource(R.drawable.gray_border_button)
            binding.btnRecommendNormal.setTextColor(getResources().getColor(R.color.select_mode_gray))
        }

        // 일반 모드 선택
        binding.btnRecommendNormal.setOnClickListener {
            selecAIMode = false

            // 일반 모드 버튼 활성화, AI 모드 버튼 비활성화
            binding.btnRecommendAi.setBackgroundResource(R.drawable.gray_border_button)
            binding.btnRecommendAi.setTextColor(getResources().getColor(R.color.select_mode_gray))
            binding.btnRecommendNormal.setBackgroundResource(R.drawable.green_solid_button)
            binding.btnRecommendNormal.setTextColor(getResources().getColor(R.color.white))
        }

        // 선택 완료 버튼 선택 시
        binding.btnSelectDone.setOnClickListener {
            // 메인 페이지로 이동
            // 선택값 전달 => true: AI 모드 / false: 일반 모드
            val mainActivity = Intent(this, MainActivity::class.java)
            intent.putExtra("select_ai", selecAIMode.toString())
            startActivity(mainActivity)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}