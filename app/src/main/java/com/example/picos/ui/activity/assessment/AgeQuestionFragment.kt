package com.example.picos.ui.activity.assessment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentAgeQuestionBinding

class AgeQuestionFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentAgeQuestionBinding

    lateinit var ageQuestList: ArrayList<AgeQuestion>

    private var selectedAgeQuest: Int = 0
    private var currentAgeQuest: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgeQuestionBinding.inflate(layoutInflater)

        ageQuestList = AgeQuest.getAgeQuestion()

        binding.tvOptOneAge.setOnClickListener(this)
        binding.tvOptTwoAge.setOnClickListener(this)
        binding.tvOptThreeAge.setOnClickListener(this)

        binding.btnAgeNext.setOnClickListener(this)

        setAgeQuestion()

        return binding.root
    }


    private fun setAgeQuestion() {
        var question: AgeQuestion = ageQuestList[currentAgeQuest-1]
        binding.tvAgequestion.text = question.questionAge
        binding.tvAgedesc.text = question.descAge
        binding.tvOptOneAge.text = question.optiOneAge
        binding.tvOptTwoAge.text = question.optiTwoAge
        binding.tvOptThreeAge.text = question.optiThreeAge

        setAgeAppearance()
    }

    private fun setAgeAppearance() {
       val options = ArrayList<TextView>()
        options.add(0, binding.tvOptOneAge)
        options.add(1, binding.tvOptTwoAge)
        options.add(2, binding.tvOptThreeAge)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            //default appearance
            option.typeface = Typeface.DEFAULT
            option.background = context?.let { ContextCompat.getDrawable(it, R.drawable.default_option_border_bg) }
        }
        }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_optOneAge -> {
                selectedOptColor(binding.tvOptOneAge, 1)
            }
            R.id.tv_optTwoAge -> {
                selectedOptColor(binding.tvOptTwoAge, 2)
            }
            R.id.tv_optThreeAge -> {
                selectedOptColor(binding.tvOptThreeAge, 3)
            }
            R.id.btnAgeNext -> {
                val nextFragment = QuestionsFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.myNavHostFragment, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }

    private fun selectedOptColor(tv: TextView, selectedAgeQuestion: Int) {
        setAgeAppearance()

        selectedAgeQuest = selectedAgeQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }

    }


