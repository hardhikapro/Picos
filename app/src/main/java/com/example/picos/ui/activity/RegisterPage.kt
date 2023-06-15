package com.example.picos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.picos.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class RegisterPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        binding.buttonCreateAccount.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.emailAddressRegister.text.toString()
        val password = binding.passwordRegister.text.toString()
        val confirmPassword = binding.confirmPasswordRegister.text.toString()

        if (password != confirmPassword) {
            // Password dan konfirmasi password tidak cocok
            // Lakukan penanganan kesalahan di sini
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registrasi berhasil, pengguna telah ditambahkan ke Firebase Authentication
                    val user: FirebaseUser? = auth.currentUser
                    // Lakukan tindakan selanjutnya, seperti menavigasi ke halaman beranda
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    // Registrasi gagal
                    val exception: Exception? = task.exception
                    if (exception is FirebaseAuthException) {
                        val errorCode = (exception as FirebaseAuthException).errorCode
                        val errorMessage = exception.message
                        Log.e("RegisterPage", "Registration failed. Error: $errorCode, $errorMessage")
                    }
                    // Lakukan penanganan kesalahan di sini
                }
            }
    }
}