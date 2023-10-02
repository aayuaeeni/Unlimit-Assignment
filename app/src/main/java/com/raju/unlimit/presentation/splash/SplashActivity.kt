package com.raju.unlimit.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.raju.unlimit.common.Constants.SPLASH_TIME
import com.raju.unlimit.databinding.ActivitySplashBinding
import com.raju.unlimit.presentation.base.BaseActivity
import com.raju.unlimit.presentation.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()
        openApp()
    }

    private fun openApp() {
        MainScope().launch{
            delay(SPLASH_TIME)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}