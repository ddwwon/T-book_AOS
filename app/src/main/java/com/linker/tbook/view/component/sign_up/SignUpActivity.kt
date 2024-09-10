package com.linker.tbook.view.component.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivitySignUpBinding
import com.linker.tbook.view.base.BaseActivity
import com.linker.tbook.view.component.login.LoginActivity
import com.linker.tbook.view.component.select_mode.SelectModeActivity

// 회원가입
class SignUpActivity : BaseActivity() {
    // ViewBinding Setting
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener { backToLoginScreen() }
        // 회원가입 버튼 선택 시
        binding.btnSignUp.setOnClickListener { navigateToSelectModeScreen() }
    }

    override fun initViewBinding() {
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }

    // 회원가입 페이지 닫고 로그인 페이지로 이동하기
    private fun backToLoginScreen() {
        val loginActivity = Intent(this, LoginActivity::class.java)
        startActivity(loginActivity)
        finish()
    }

    // 사용방식 선택 페이지로 이동
    private fun navigateToSelectModeScreen() {
        val selectModeActivity = Intent(this, SelectModeActivity::class.java)
        startActivity(selectModeActivity)
        finish()
    }

    private var doubleBackToExit = false
    // 이전 버튼 - 폰에 있는 이전 버튼
    override fun onBackPressed() {
        //super.onBackPressed()

        if (doubleBackToExit) {
            // 두 번 누르면 회원가입 페이지 닫기
            finish()
        } else {
            // 한 번 누르면 종료 Toast 안내
            Toast.makeText(this, getString(R.string.toast_back_signup_page), Toast.LENGTH_SHORT).show()
            doubleBackToExit = true

            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }
    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}