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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentCategoricalQuestionBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel


class CategoricalQuestionFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentCategoricalQuestionBinding

    lateinit var catQuestList: ArrayList<CategoricalQuestion>


    private var selectedQuest: Int = 0
    private var currentQuest: Int = 1
    private lateinit var assessmentViewModel: SelfAssessmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoricalQuestionBinding.inflate(layoutInflater)

        catQuestList = CategoricalQuest.getCategoricalQuestion()

        binding.tvOptOneCat.setOnClickListener(this)
        binding.tvOptTwoCat.setOnClickListener(this)
        binding.tvOptThreeCat.setOnClickListener(this)
        binding.tvOptFourCat.setOnClickListener(this)
        binding.tvOptFifthCat.setOnClickListener(this)
        binding.tvOptSixthCat.setOnClickListener(this)
        binding.tvOptSevenCat.setOnClickListener(this)
        binding.tvOptEightCat.setOnClickListener(this)
        binding.btnCatNext.setOnClickListener(this)
        assessmentViewModel =
            ViewModelProvider(requireActivity()).get(SelfAssessmentViewModel::class.java)


        setCatQuestion()

        return binding.root
    }

    private fun setCatQuestion() {
        var question: CategoricalQuestion = catQuestList[currentQuest - 1]
        binding.tvCatquestion.text = question.questionCat
        binding.tvCatdesc.text = question.descCat
        binding.tvOptOneCat.text = question.optiOneCat
        binding.tvOptTwoCat.text = question.optiTwoCat
        binding.tvOptThreeCat.text = question.optiThreeCat
        binding.tvOptFourCat.text = question.optiFourCat
        binding.tvOptFifthCat.text = question.optiFiveCat
        binding.tvOptSixthCat.text = question.optiSixCat
        binding.tvOptSevenCat.text = question.optiSevenCat
        binding.tvOptEightCat.text = question.optiEightCat

        setCatAppearance()

    }

    private fun setCatAppearance() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptOneCat)
        options.add(1, binding.tvOptTwoCat)
        options.add(2, binding.tvOptThreeCat)
        options.add(3, binding.tvOptFourCat)
        options.add(4, binding.tvOptFifthCat)
        options.add(5, binding.tvOptSixthCat)
        options.add(6, binding.tvOptSevenCat)
        options.add(7, binding.tvOptEightCat)

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

            R.id.tv_optOneCat -> {
                selectedOptColor(binding.tvOptOneCat, 1)
                assessmentViewModel.bloodGroup = "11"
            }

            R.id.tv_optTwoCat -> {
                selectedOptColor(binding.tvOptTwoCat, 2)
                assessmentViewModel.bloodGroup = "12"
            }

            R.id.tv_optThreeCat -> {
                selectedOptColor(binding.tvOptThreeCat, 3)
                assessmentViewModel.bloodGroup = "13"
            }

            R.id.tv_optFourCat -> {
                selectedOptColor(binding.tvOptFourCat, 4)
                assessmentViewModel.bloodGroup = "14"
            }
            R.id.tv_optFifthCat -> {
                selectedOptColor(binding.tvOptFifthCat, 5)
                assessmentViewModel.bloodGroup = "15"
            }

            R.id.tv_optSixthCat -> {
                selectedOptColor(binding.tvOptSixthCat, 6)
                assessmentViewModel.bloodGroup = "16"
            }

            R.id.tv_optSevenCat -> {
                selectedOptColor(binding.tvOptSevenCat, 7)
                assessmentViewModel.bloodGroup = "17"
            }

            R.id.tv_optEightCat -> {
                selectedOptColor(binding.tvOptEightCat, 8)
                assessmentViewModel.bloodGroup = "18"
            }

            R.id.btnCatNext -> {
                //ketika tidak ada opsi yg dipilih
                if (selectedQuest == 0) {
                    currentQuest++

                    when {
                        currentQuest <= catQuestList.size -> {

                            setCatQuestion()

                        }
                        else -> {

                            val fragment = MenstrualCycleFragment()
                            val fragmentManager = requireActivity().supportFragmentManager
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(
                                R.id.myNavHostFragment,
                                fragment
                            ) // Ganti R.id.container dengan ID dari kontainer tempat Anda ingin menampilkan fragment
                            transaction.addToBackStack(null) // Jika Anda ingin menambahkan fragment ini ke back stack
                            transaction.commit()

                        }
                    }
                } else {
                    if (currentQuest == catQuestList.size) {
                        binding.btnCatNext.text = "NEXT"
                        val fragment = MenstrualCycleFragment()
                        val fragmentManager = requireActivity().supportFragmentManager
                        val transaction = fragmentManager.beginTransaction()
                        transaction.replace(
                            R.id.myNavHostFragment,
                            fragment
                        ) // Ganti R.id.container dengan ID dari kontainer tempat Anda ingin menampilkan fragment
                        transaction.addToBackStack(null) // Jika Anda ingin menambahkan fragment ini ke back stack
                        transaction.commit()
                    } else {

                        binding.btnCatNext.text = "NEXT"
                    }
                    selectedQuest = 0
                }


            }

        }
    }


    //atur warna option ketika diklik
    private fun selectedOptColor(tv: TextView, selectedQuestion: Int) {
        setCatAppearance()

        selectedQuest = selectedQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }


}




