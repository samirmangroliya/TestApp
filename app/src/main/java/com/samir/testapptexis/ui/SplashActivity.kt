package com.samir.testapptexis.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.samir.testapptexis.databinding.ActivitySplashBinding
import com.samir.testapptexis.global.GlobalConstants
import com.samir.testapptexis.global.PreferenceHelper

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                goToNextScreen()
            }
        }, GlobalConstants.SPLASH_TIME)
    }

    private fun goToNextScreen() {
        try {
            val isLoggedIn = PreferenceHelper.getValueFromKey(this, "isloggedin")
            if (isLoggedIn == true) {
                val intent = Intent(this@SplashActivity, DashBoardActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            this.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}