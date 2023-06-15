package com.example.picos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.picos.databinding.ActivityWelcomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class WelcomePage : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomePageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login berhasil, dapatkan informasi pengguna
                        val user: FirebaseUser? = firebaseAuth.currentUser
                        user?.let {user ->
                            firebaseAuth.updateCurrentUser(user)
                        }
                        val intent = Intent (this, MainActivity::class.java)
                        startActivity(intent)
//                        val displayName = user?.displayName
//                        val email = user?.email
//                        val uid = user?.uid


                        // Lakukan tindakan setelah login berhasil
                        // Misalnya, pindah ke halaman beranda
                        Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    } else {
                        // Login gagal, tampilkan pesan kesalahan
                        val errorMessage = task.exception?.message
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}