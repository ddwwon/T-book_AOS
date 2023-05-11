package com.linker.tbook.view.component.select_mode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.linker.tbook.view.component.MainActivity
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivitySelectModeBinding
import com.linker.tbook.view.base.BaseActivity
import com.linker.tbook.view.component.login.LoginActivity

// 사용방식 선택 - AI 추천 모드, 일반 모드
class SelectModeActivity : BaseActivity() {

    // ViewBinding Setting
    private var _binding: ActivitySelectModeBinding? = null
    private val binding get() = _binding!!

    // AI 모드 선택했는지 유무
    private var selecAIMode: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 이전에 선택한 값이 저장되어 있는 경우 적용해주기
        // selectMode =
        // 일단 임의로 AI 값 저장해둠
        selecAIMode = true

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener { backToLoginScreen() }
        // AI 모드 선택
        binding.btnRecommendAi.setOnClickListener { selectAIMode() }
        // 일반 모드 선택
        binding.btnRecommendNormal.setOnClickListener { selectNormalMode() }
        // 선택 완료 버튼 선택 시
        binding.btnSelectDone.setOnClickListener { navigateToMainScreen() }

    }

    override fun initViewBinding() {
        _binding = ActivitySelectModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }

    // 사용방식 선택 페이지 닫고 로그인 페이지로 이동하기
    private fun backToLoginScreen() {
        val loginActivity = Intent(this, LoginActivity::class.java)
        startActivity(loginActivity)
        finish()
    }

    // 메인 페이지로 이동
    private fun navigateToMainScreen() {
        // 선택값 전달 => true: AI 모드 / false: 일반 모드
        val mainActivity = Intent(this, MainActivity::class.java)
        intent.putExtra("select_ai", selecAIMode.toString())
        startActivity(mainActivity)
        finish()
    }

    // AI 모드 선택 시
    private fun selectAIMode() {
        selecAIMode = true

        // AI 모드 버튼 활성화, 일반 모드 버튼 비활성화
        binding.btnRecommendAi.setBackgroundResource(R.drawable.green_solid_button)
        binding.btnRecommendAi.setTextColor(getResources().getColor(R.color.white))
        binding.btnRecommendNormal.setBackgroundResource(R.drawable.gray_border_button)
        binding.btnRecommendNormal.setTextColor(getResources().getColor(R.color.select_mode_gray))
    }

    // 일반 모드 선택 시
    private fun selectNormalMode() {
        selecAIMode = false

        // 일반 모드 버튼 활성화, AI 모드 버튼 비활성화
        binding.btnRecommendAi.setBackgroundResource(R.drawable.gray_border_button)
        binding.btnRecommendAi.setTextColor(getResources().getColor(R.color.select_mode_gray))
        binding.btnRecommendNormal.setBackgroundResource(R.drawable.green_solid_button)
        binding.btnRecommendNormal.setTextColor(getResources().getColor(R.color.white))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private var doubleBackToExit = false
    // 이전 버튼 - 폰에 있는 이전 버튼
    override fun onBackPressed() {
        //super.onBackPressed()

        if (doubleBackToExit) {
            // 두 번 누르면 비밀번호 찾기 페이지 닫기
            finish()
        } else {
            // 한 번 누르면 종료 Toast 안내
            Toast.makeText(this, getString(R.string.toast_back_main_page), Toast.LENGTH_SHORT).show()
            doubleBackToExit = true

            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }
    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }
}