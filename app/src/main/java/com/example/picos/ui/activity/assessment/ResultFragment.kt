package com.example.picos.ui.activity.assessment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.picos.R
import com.example.picos.databinding.FragmentResultBinding
import com.example.picos.ui.activity.MainActivity
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

lateinit var binding: FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var viewModel: SelfAssessmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        viewModel = ViewModelProvider(requireActivity())[SelfAssessmentViewModel::class.java]

        viewModel.result.observe(viewLifecycleOwner, {
            binding.asResultDesc.text = it
        })
        binding.btnResult.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}