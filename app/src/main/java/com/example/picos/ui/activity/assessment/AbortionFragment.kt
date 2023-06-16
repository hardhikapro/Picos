package com.example.picos.ui.activity.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentAbortionBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

class AbortionFragment : Fragment() {
    private var _binding: FragmentAbortionBinding? = null
    private val binding get() = _binding!!
    private lateinit var assessmentViewModel: SelfAssessmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbortionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assessmentViewModel = ViewModelProvider(requireActivity()).get(SelfAssessmentViewModel::class.java)

        // Set the click listener for the button
        binding.btnAbortionNext.setOnClickListener {
            // Get the user input from the EditText
            val userInput = binding.abortionAnswer.text.toString()
            assessmentViewModel.abortion = userInput

            // Save the user input to a shared view model or any other appropriate storage mechanism

            // Navigate to the CategoricalFragment
            findNavController().navigate(R.id.action_BMIFragment_to_categoricalQuestionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
