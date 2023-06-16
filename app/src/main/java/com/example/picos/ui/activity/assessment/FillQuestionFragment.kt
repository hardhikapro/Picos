package com.example.picos.ui.activity.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentFillQuestionBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel


class FillQuestionFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentFillQuestionBinding
    lateinit var fillQuestList: ArrayList<FillQuestion>
    private var selectedQuest: Int = 0
    private var currentQuest: Int = 1
    private lateinit var assessmentViewModel : SelfAssessmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFillQuestionBinding.inflate(layoutInflater)

        assessmentViewModel = ViewModelProvider(requireActivity()).get(SelfAssessmentViewModel::class.java)

        fillQuestList = FillQuest.getFillQuestion()

        binding.btnFillNext.setOnClickListener(this)
        (activity as AppCompatActivity).supportActionBar?.hide()

        setFillQuestion()

        return binding.root
    }

    private fun setFillQuestion() {
        var question: FillQuestion = fillQuestList[currentQuest-1]
        binding.tvFillquestion.text = question.questionFill
        binding.tvFilldesc.text = question.descFill
        binding.fillAnswer.id = question.idEditText
//        assessmentViewModel.WaistHipRatio = if binding.fillAnswer.id.toString() == "waistToHip"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFill_next -> {
                if(selectedQuest == 0 ) {
                    currentQuest++
                    if(currentQuest <= fillQuestList.size) {
                        setFillQuestion()
                    } else {


                    val nextFragment = CategoricalQuestionFragment()
                    val fragmentManager = requireActivity().supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.myNavHostFragment, nextFragment)
//                    fragmentTransaction.addToBackStack()
                    fragmentTransaction.commit()
                }

            }
        }
    }
}}

