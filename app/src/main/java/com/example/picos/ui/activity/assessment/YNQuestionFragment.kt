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
import com.example.picos.R
import com.example.picos.databinding.FragmentQuestionBinding
import com.example.picos.databinding.FragmentYNQuestionBinding

class YNQuestionFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentYNQuestionBinding

    lateinit var yNQuestList: ArrayList<YnQuestion>

    private var selectedQuest: Int = 0
    private var currentQuest: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYNQuestionBinding.inflate(layoutInflater)

        yNQuestList = YnQuest.getYnQuestion()
        binding.tvOptOneYn.setOnClickListener(this)
        binding.tvOptTwoYn.setOnClickListener(this)
        binding.btnYnNext.setOnClickListener(this)

        setYnQuestion()

        return binding.root
    }

    private fun setYnQuestion() {
        var question: YnQuestion = yNQuestList[currentQuest - 1]
        binding.tvYnquestion.text = question.questionYn
        binding.tvYndesc.text = question.descYn
        binding.tvOptOneYn.text = question.optiOneYn
        binding.tvOptTwoYn.text = question.optiTwoYn

        setYnAppearance()

    }


    private fun setYnAppearance() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptOneYn)
        options.add(1, binding.tvOptTwoYn)

        for (option in options) {

            option.setTextColor(Color.parseColor("#7A8089"))
            //default appearance
            option.typeface = Typeface.DEFAULT
            option.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.default_option_border_bg) }


        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optOneYn -> {
                selectedOptColor(binding.tvOptOneYn, 1)
            }

            R.id.tv_optTwoYn -> {
                selectedOptColor(binding.tvOptTwoYn, 2)
            }

            R.id.btnYn_next -> {
                if(selectedQuest == 0 ) {
                    currentQuest++
                    if(currentQuest <= yNQuestList.size) {
                        setYnQuestion()
                    } else {
                        val nextFragment = AgeQuestionFragment()
                        val fragmentManager = requireActivity().supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.myNavHostFragment, nextFragment)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                    }
                } else {
                    if (currentQuest == yNQuestList.size) {
                        binding.btnYnNext.text = "Finished"
                    } else {
                        binding.btnYnNext.text = "NEXT"
                    }
                    selectedQuest = 0

                }
            }
        }
    }

    private fun selectedOptColor(tv: TextView, selectedQuestion: Int) {
        setYnAppearance()


        selectedQuest = selectedQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }

}




