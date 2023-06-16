package com.example.picos.ui.activity.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picos.R



class MainDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

//        button.setOnClickListener {
//            val fragment = MyFragment()
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.container, fragment)
//            transaction.addToBackStack(null) // Tambahkan fragment ke back stack
//            transaction.commit()
//        }
    }

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStack()
//        } else {
//            super.onBackPressed()
//        }
//    }
}

