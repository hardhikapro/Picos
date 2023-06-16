package com.example.picos.ui.activity.assessment

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentQuestionBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

class QuestionsFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentQuestionBinding

    lateinit var dummyQuestList: ArrayList<DummyQuestions>

    private var selectedQuest: Int = 0
    private var currentQuest: Int = 1
    private lateinit var assessmentViewModel: SelfAssessmentViewModel
    private lateinit var mariage: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container,false)
        binding = FragmentQuestionBinding.inflate(layoutInflater)

        dummyQuestList = DummyQuest.getDummyQuestions()

        binding.tvOptOne.setOnClickListener(this)
        binding.tvOptTwo.setOnClickListener(this)
        binding.tvOptThree.setOnClickListener(this)
        binding.tvOptFour.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        assessmentViewModel =
            ViewModelProvider(requireActivity())[SelfAssessmentViewModel::class.java]

        setQuestions()



        return binding.root
    }

    private fun setQuestions() {
        var question: DummyQuestions = dummyQuestList[currentQuest - 1]
        binding.tvquestion.text = question.question
        binding.tvMarrydesc.text = question.descMarry
        binding.tvOptOne.text = question.optiOne
        binding.tvOptTwo.text = question.optiTwo
        binding.tvOptThree.text = question.optiThree
        binding.tvOptFour.text = question.optiFour


        setAppearance()


        //implementasi finished button di pertanyaan terakhir
        if (currentQuest == dummyQuestList.size) {
            binding.btnNext.text = getString(R.string.button_finished)
        }
    }

    //setting warna opsi jawaban
    private fun setAppearance() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptOne)
        options.add(1, binding.tvOptTwo)
        options.add(2, binding.tvOptThree)
        options.add(3, binding.tvOptFour)

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

            R.id.tv_optOne -> {
                selectedOptColor(binding.tvOptOne, 1)
                mariage = "1"
            }

            R.id.tv_optTwo -> {
                selectedOptColor(binding.tvOptTwo, 2)
                mariage = "2"
            }

            R.id.tv_optThree -> {
                selectedOptColor(binding.tvOptThree, 3)
                mariage = "3"
            }

            R.id.tv_optFour -> {
                selectedOptColor(binding.tvOptFour, 4)
                mariage = "4"
            }

            R.id.btn_next -> {

                assessmentViewModel.marriageCategory = mariage
                // Navigasi ke ResultFragment
                assessmentViewModel.isLoading.observe(viewLifecycleOwner, {
                    showLoading(it)
                })
                assessmentViewModel.predict()
                val nextFragment = ResultFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.myNavHostFragment, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

            }


        }

    }

    private fun selectedOptColor(tv: TextView, selectedQuestion: Int) {
        setAppearance()

        selectedQuest = selectedQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
        binding.btnNext.visibility = if (state) View.GONE else View.VISIBLE
    }
}

