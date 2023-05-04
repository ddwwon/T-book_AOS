package com.linker.tbook.view.login.findPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivityChangePasswordBinding

// 비밀번호 찾기 - 비밀번호 변경
class ChangePasswordActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 뒤로가기 버튼 > 비밀번호 찾기 페이지 닫기
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 번호 입력 후 엔터키 이벤트 처리
        binding.edittextInputPasswordConfirm.setOnEditorActionListener{v, id, event ->
            if(id == EditorInfo.IME_ACTION_GO){
                // 올바른 정보인지 확인

                // 변경 정보 적용

                // 페이지 닫기
                finish()
            }
            true
        }

        // 비밀번호 변경 버튼 선택
        binding.btnDoneChangePassword.setOnClickListener {
            // 올바른 정보인지 확인

            // 변경 정보 적용

            // 페이지 닫기
            finish()
        }
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
            Toast.makeText(this, getString(R.string.toast_back_page), Toast.LENGTH_SHORT).show()
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