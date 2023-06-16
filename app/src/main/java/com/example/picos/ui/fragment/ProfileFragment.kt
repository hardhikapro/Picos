package com.example.picos.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.picos.R
import com.example.picos.ui.activity.CommingSoon
import com.example.picos.ui.activity.LoginPage
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var btnLogout: Button
    private lateinit var btnEdit : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        btnLogout = view.findViewById(R.id.btnLogout)

        btnEdit = view.findViewById(R.id.btnEditProfile)
        btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), CommingSoon::class.java)
            startActivity(intent)
        }

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(requireContext(), LoginPage::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}
