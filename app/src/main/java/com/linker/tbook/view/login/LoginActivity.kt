package com.linker.tbook.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linker.tbook.MainActivity
import com.linker.tbook.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // ViewBinding Setting
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}