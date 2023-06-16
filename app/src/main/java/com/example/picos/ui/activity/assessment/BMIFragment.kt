package com.example.picos.ui.activity.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

class BMIFragment : Fragment() {
    private lateinit var assessmentViewModel : SelfAssessmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b_m_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weightEditText: EditText = view.findViewById(R.id.fill_answer_weight)
        val heightEditText: EditText = view.findViewById(R.id.fill_answer_height)
        val nextButton: Button = view.findViewById(R.id.btnBmi_next)
        assessmentViewModel = ViewModelProvider(requireActivity()).get(SelfAssessmentViewModel::class.java)

        nextButton.setOnClickListener {
            val weight = weightEditText.text.toString()
            val height = heightEditText.text.toString()
            val bmi = weight.toInt() / height.toInt()


            assessmentViewModel.BMI = bmi.toString()

//            val bundle = Bundle().apply {
//                putString("weight", weight)
//                putString("height", height)
//            }

            findNavController().navigate(R.id.action_BMIFragment_to_WaistHipRatioFragment)
        }
    }
}
