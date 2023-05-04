package com.linker.tbook.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.linker.tbook.MainActivity
import com.linker.tbook.databinding.ActivityLoginBinding
import com.linker.tbook.view.login.findPassword.FindPasswordActivity

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

            // 메인 페이지로 이동
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}