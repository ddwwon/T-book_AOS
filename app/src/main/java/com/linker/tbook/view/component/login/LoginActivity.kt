package com.linker.tbook.view.component.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.linker.tbook.databinding.ActivityLoginBinding
import com.linker.tbook.view.component.login.findPassword.FindPasswordActivity
import com.linker.tbook.view.component.select_mode.SelectModeActivity

class LoginActivity : AppCompatActivity() {

    // ViewBinding Setting
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 비밀번호를 잊으셨나요?
        binding.textbtnGotoFindPassword.setOnClickListener(View.OnClickListener {
            // 비밀번호 찾기 페이지로 이동
            val findPasswordActivity = Intent(this, FindPasswordActivity::class.java)
            startActivity(findPasswordActivity)
        })

        // 로그인 버튼 선택 시
        binding.btnLogin.setOnClickListener {

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