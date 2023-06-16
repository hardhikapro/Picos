package com.example.picos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picos.R
import com.example.picos.databinding.ActivityCommingSoonBinding
import com.example.picos.databinding.FragmentRegularExerciseBinding
import com.google.android.play.core.integrity.IntegrityTokenRequest

class CommingSoon : AppCompatActivity() {
    private lateinit var binding: ActivityCommingSoonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommingSoonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}