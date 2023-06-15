package com.example.picos.ui.activity.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picos.R

class OnBoardingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)
        supportActionBar?.hide()
    }
}