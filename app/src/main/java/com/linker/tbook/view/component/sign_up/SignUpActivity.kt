package com.linker.tbook.view.component.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linker.tbook.databinding.ActivitySignUpBinding
import com.linker.tbook.view.component.select_mode.SelectModeActivity

// 회원가입
class SignUpActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼 > 회원가입 페이지 닫기
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 회원가입 버튼 선택 시
        binding.btnSignUp.setOnClickListener {

            // 사용방식 선택 페이지로 이동
            val selectModeActivity = Intent(this, SelectModeActivity::class.java)
            startActivity(selectModeActivity)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}