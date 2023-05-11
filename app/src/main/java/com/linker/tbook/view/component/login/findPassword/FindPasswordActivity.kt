package com.linker.tbook.view.component.login.findPassword

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivityFindPasswordBinding
import com.linker.tbook.view.base.BaseActivity


// 비밀번호 찾기 - 정보 입력
class FindPasswordActivity : BaseActivity() {
    // ViewBinding Setting
    private var _binding: ActivityFindPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 번호 입력시 자동 하이픈 추가
        binding.edittextInputPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        // 뒤로가기 버튼 > 비밀번호 찾기 페이지 닫기
        binding.btnBack.setOnClickListener { finish() }

        // 번호 입력 후 엔터키 이벤트 처리
        binding.edittextInputPhone.setOnEditorActionListener{v, id, event ->
            if(id == EditorInfo.IME_ACTION_GO){
                // 올바른 정보인지 확인

                // 비밀번호 변경 페이지로 이동
                navigateToChangePasswordScreen()
            }
            true
        }

        // 비밀번호 변경 버튼 선택
        binding.btnChangePassword.setOnClickListener {
            // 올바른 정보인지 확인

            // 비밀번호 변경 페이지로 이동
            navigateToChangePasswordScreen()
        }
    }

    override fun initViewBinding() {
        _binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }

    // 비밀번호 변경 페이지로 이동
    private fun navigateToChangePasswordScreen() {
        val changePasswordActivity = Intent(this, ChangePasswordActivity::class.java)
        startActivity(changePasswordActivity)
        finish()
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
            Toast.makeText(this, getString(R.string.toast_back_fp_page), Toast.LENGTH_SHORT).show()
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