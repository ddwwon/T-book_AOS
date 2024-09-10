package com.linker.tbook.view.component.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.linker.tbook.data.Resource
import com.linker.tbook.data.dto.login.LoginResponse
import com.linker.tbook.databinding.ActivityLoginBinding
import com.linker.tbook.utils.*
import com.linker.tbook.view.base.BaseActivity
import com.linker.tbook.view.component.login.findPassword.FindPasswordActivity
import com.linker.tbook.view.component.select_mode.SelectModeActivity
import com.linker.tbook.view.component.sign_up.SignUpActivity

class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입 버튼 선택 시
        binding.textbtnGotoSignup.setOnClickListener { navigateToSignUpScreen() }
        // 비밀번호를 잊으셨나요?
        binding.textbtnGotoFindPassword.setOnClickListener(View.OnClickListener { navigateToFindPasswordScreen() })
        // 로그인 버튼 선택 시
        binding.btnLogin.setOnClickListener { navigateToSelectModeScreen() }
    }

    override fun initViewBinding() {
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        //observeSnackBarMessages(loginViewModel.showSnackBar)
        observeToast(loginViewModel.showToast)
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> {
                Log.d("Login", "Loading ------")
                // binding.loaderView.toVisible()
            }
            is Resource.Success -> status.data?.let {
                //binding.loaderView.toGone()
                navigateToSelectModeScreen()
            }
            is Resource.DataError -> {
                Log.d("Login", "Loading ------")
                //binding.loaderView.toGone()
                //status.errorCode?.let { loginViewModel.showToastMessage(it) }
            }
        }
    }

    // 사용방식 선택 페이지로 이동
    private fun navigateToSelectModeScreen() {
        val selectModeActivity = Intent(this, SelectModeActivity::class.java)
        startActivity(selectModeActivity)
        finish()
    }

    // 회원가입 페이지로 이동
    private fun navigateToSignUpScreen() {
        val signUpActivity = Intent(this, SignUpActivity::class.java)
        startActivity(signUpActivity)
    }

    // 비밀번호 찾기 페이지로 이동
    private fun navigateToFindPasswordScreen() {
        val findPasswordActivity = Intent(this, FindPasswordActivity::class.java)
        startActivity(findPasswordActivity)
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        //binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}