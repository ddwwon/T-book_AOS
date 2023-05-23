package com.linker.tbook.view.component

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
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

//    private var doubleBackToExit = false
//    // 이전 버튼 - 폰에 있는 이전 버튼
//    override fun onBackPressed() {
//        //super.onBackPressed()
//
//        if (doubleBackToExit) {
//            // 두 번 누르면 앱 종료
//            finishAffinity()
//        } else {
//            // 한 번 누르면 종료 Toast 안내
//            Toast.makeText(this, getString(R.string.toast_back_main_page), Toast.LENGTH_SHORT).show()
//            doubleBackToExit = true
//
//            runDelayed(1500L) {
//                doubleBackToExit = false
//            }
//        }
//    }
//    fun runDelayed(millis: Long, function: () -> Unit) {
//        Handler(Looper.getMainLooper()).postDelayed(function, millis)
//    }
}