package com.linker.tbook.view.component

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.linker.tbook.R
import com.linker.tbook.databinding.ActivityMainBinding
import com.linker.tbook.utils.*
import com.linker.tbook.view.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // NavController
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // NavController
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Navigation Graph를 사용해서 Bottom Navigation 설정
        binding.navBottom.setupWithNavController(navController)

        // 최상위 화면을 제외하고는 BottomNavigation Bar 없애기
        setBottomNavigation()
    }

    override fun initViewBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {
        //observe(mainViewModel.mainLiveData, ::handleLoginResult)
        //observeSnackBarMessages(mainViewModel.showSnackBar)
        //observeToast(mainViewModel.showToast)
    }

    private fun setBottomNavigation() {
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.menu_home ||
                    destination.id == R.id.menu_recommend_result ||
                    destination.id == R.id.menu_myPage
            ) {
                binding.navBottom.visibility = View.VISIBLE
            } else {
                binding.navBottom.visibility = View.GONE
            }
        }
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

    // 메인 화면(=하단바로 바로 들어가지는 페이지)들에서 이전 버튼 2번 누르면 앱 종료
    var waitTime = 0L
    override fun onBackPressed() {
        if(navController.currentDestination?.id == R.id.menu_home ||
            navController.currentDestination?.id == R.id.menu_recommend_result ||
            navController.currentDestination?.id == R.id.menu_myPage) {
            if (System.currentTimeMillis() - waitTime >= 1500) {
                waitTime = System.currentTimeMillis()
                Toast.makeText(this, getString(R.string.toast_back_main_page), Toast.LENGTH_SHORT).show()
            } else {
                finish() // 액티비티 종료
            }
        }
        else{
            super.onBackPressed()
        }
    }
}