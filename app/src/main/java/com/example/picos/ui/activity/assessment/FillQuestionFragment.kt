package com.example.picos.ui.activity.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.picos.R
import com.example.picos.databinding.FragmentFillQuestionBinding

class FillQuestionFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentFillQuestionBinding
    lateinit var fillQuestList: ArrayList<FillQuestion>
    private var selectedQuest: Int = 0
    private var currentQuest: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFillQuestionBinding.inflate(layoutInflater)

        fillQuestList = FillQuest.getFillQuestion()

        binding.btnFillNext.setOnClickListener(this)
        (activity as AppCompatActivity).supportActionBar?.hide()



        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFill_next -> {
                val nextFragment = CategoricalQuestionFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.myNavHostFragment, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }
}

