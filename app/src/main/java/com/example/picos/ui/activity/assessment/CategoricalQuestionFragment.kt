package com.example.picos.ui.activity.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentCategoricalQuestionBinding

class CategoricalQuestionFragment : Fragment() {

    private var _binding: FragmentCategoricalQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoricalQuestionBinding.inflate(inflater, container, false)

        binding.btnYnNext.setOnClickListener {
            val nextFragment = YNQuestionFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.myNavHostFragment, nextFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
