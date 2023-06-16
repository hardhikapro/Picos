package com.example.picos.ui.activity.assessment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentMenstrualCycleBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

class MenstrualCycleFragment : Fragment(), View.OnClickListener {

    private var selectedOption: String = ""
    private var _binding: FragmentMenstrualCycleBinding? = null
    private val binding get() = _binding!!
    private lateinit var assessmentViewModel: SelfAssessmentViewModel
    private lateinit var cycleResult: String
    private var selectedQuest: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenstrualCycleBinding.inflate(inflater, container, false)

        binding.tvOptOneMenstrual.setOnClickListener(this)
        binding.tvOptTwoMenstrual.setOnClickListener(this)

        setMensAppearance()

        return binding.root
    }

    private fun setMensAppearance() {
        val options = mutableListOf<TextView>()
        options.add(binding.tvOptOneMenstrual)
        options.add(binding.tvOptTwoMenstrual)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.default_option_border_bg) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assessmentViewModel = ViewModelProvider(requireActivity()).get(SelfAssessmentViewModel::class.java)

        val btnNext: Button = binding.btnMenstrualNext
        btnNext.setOnClickListener(this)

        binding.tvOptOneMenstrual.setOnClickListener(this)

        binding.tvOptTwoMenstrual.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optOneMenstrual -> {
                selectedOptColor(binding.tvOptOneMenstrual, 1)
                cycleResult = "2"
            }

            R.id.tv_optTwoMenstrual -> {
                selectedOptColor(binding.tvOptTwoMenstrual, 2)
                cycleResult = "4"
            }

            R.id.btnMenstrual_next -> {
                assessmentViewModel.cycle = cycleResult
                val fragment = PregnantFragment()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectedOptColor(tv: TextView, selectedQuestion: Int) {
        setMensAppearance()

        selectedQuest = selectedQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }
}
