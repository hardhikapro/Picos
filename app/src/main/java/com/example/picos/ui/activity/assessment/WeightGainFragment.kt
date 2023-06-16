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
import com.example.picos.databinding.FragmentWeightGainBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel

class WeightGainFragment : Fragment(), View.OnClickListener {
    private var selectedOption: String = ""
    private var _binding: FragmentWeightGainBinding? = null
    private val binding get() = _binding!!
    private lateinit var assessmentViewModel: SelfAssessmentViewModel
    private lateinit var weightGain:String
    private var selectedQuest: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightGainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext: Button = binding.btnWeightGainNext
        btnNext.setOnClickListener(this)

        val optOne: TextView = binding.tvOptOneWeightGain
        val optTwo: TextView = binding.tvOptTwoWeightGain
        assessmentViewModel = ViewModelProvider(requireActivity())[SelfAssessmentViewModel::class.java]

        optOne.setOnClickListener(this)

        optTwo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optOneWeight_gain -> {
                selectedOptColor(binding.tvOptOneWeightGain, 1)
                weightGain = "1"
            }

            R.id.tv_optTwoWeight_gain -> {
                selectedOptColor(binding.tvOptTwoWeightGain, 2)
                weightGain = "0"
            }

            R.id.btnWeightGain_next -> {
                assessmentViewModel.weightGain = weightGain
                val fragment = HairGrowthFragment()
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

    private fun setMensAppearance() {
        val options = mutableListOf<TextView>()
        options.add(binding.tvOptOneWeightGain)
        options.add(binding.tvOptTwoWeightGain)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.default_option_border_bg) }
        }
    }

    private fun selectedOptColor(tv: TextView, selectedQuestion: Int) {
        setMensAppearance()

        selectedQuest = selectedQuestion

        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option_border_bg) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
