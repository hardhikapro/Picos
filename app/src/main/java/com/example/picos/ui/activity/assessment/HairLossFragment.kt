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
import com.example.picos.databinding.FragmentHairLossBinding
import com.example.picos.databinding.FragmentSkinDarkeningBinding
import com.example.picos.ui.viewModel.SelfAssessmentViewModel


class HairLossFragment : Fragment(), View.OnClickListener {
    private var selectedOption: String = ""
    private var _binding: FragmentHairLossBinding? = null
    private val binding get() = _binding!!
    private lateinit var assessmentViewModel: SelfAssessmentViewModel
    private lateinit var hairLoss:String
    private var selectedQuest: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHairLossBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext: Button = binding.btnHairLossNext
        btnNext.setOnClickListener(this)
        assessmentViewModel = ViewModelProvider(requireActivity())[SelfAssessmentViewModel::class.java]
        val optOne: TextView = binding.tvOptOneHairLoss
        val optTwo: TextView = binding.tvOptTwoHairLoss

        optOne.setOnClickListener (this)

        optTwo.setOnClickListener (this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optOneHair_loss -> {
                selectedOptColor(binding.tvOptOneHairLoss, 1)
                hairLoss = "1"
            }

            R.id.tv_optTwoHair_loss -> {
                selectedOptColor(binding.tvOptTwoHairLoss, 2)
                hairLoss = "0"
            }

            R.id.btnHairLoss_next -> {
                assessmentViewModel.hairLoss = hairLoss
                val fragment = PimplesFragment()
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
        options.add(binding.tvOptOneHairLoss)
        options.add(binding.tvOptTwoHairLoss)

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