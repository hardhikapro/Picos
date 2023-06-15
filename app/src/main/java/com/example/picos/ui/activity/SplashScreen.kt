package com.example.picos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.picos.R
import com.example.picos.ui.activity.onboarding.OnBoardingScreenActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, OnBoardingScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}