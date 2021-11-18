package com.example.myfinancialapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.myfinancialapp.databinding.FragmentFinancesBinding
import com.example.myfinancialapp.databinding.FragmentLoginBinding
import com.example.myfinancialapp.databinding.FragmentTitleBinding

//https://stackoverflow.com/questions/54097662/how-to-create-a-rectangle-with-rounded-corners-with-different-sections-in-differ
class FinancesFragment : Fragment() {
    private var _binding: FragmentFinancesBinding? = null
    private val binding get() = _binding!!

    private var goalamount : Float = 0f
    private var goaltitle : String = ""
    private var savedAmount : Float = 0f
    private var textProgress : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentFinancesBinding.inflate(inflater, container, false)

        binding.setGoalBtn.setOnClickListener{
            binding.apply {
                if (goalAmount.text.isNotEmpty() && goalTitle.text.isNotEmpty()) {

                    goalAmount.hideKeyboard()
                    goalTitle.hideKeyboard()

                    goaltitle = goalTitle.text.toString()
                    goalamount = goalAmount.text.toString().toFloat()
                    "%.2f".format(goalamount)

                    instructionText.visibility = View.GONE
                    goalAmount.visibility = View.GONE
                    goalTitle.visibility = View.GONE
                    setGoalBtn.visibility = View.GONE

                    newTitle.visibility = View.VISIBLE
                    newGoalValue.visibility = View.VISIBLE
                    amountToAdd.visibility = View.VISIBLE
                    amountAddBtn.visibility = View.VISIBLE
                    determinateBar.visibility = View.VISIBLE
                    progressText.visibility = View.VISIBLE

                    newTitle.text = goaltitle
                    val textholder = "R$ " + goalamount.toString()
                    newGoalValue.text = textholder

                    textProgress = "R$ $savedAmount / R$ ${goalamount.toString()}"

                    progressText.text = textProgress

                    //val displayedText = "Objetivo: $goal_title\n Valor: R$ $goal_amount"
                    //overviewText.text =  displayedText
                    //overviewText.visibility=View.VISIBLE
                }
            }

            binding.amountAddBtn.setOnClickListener{
                if (binding.amountToAdd.text.isNotEmpty()) {
                    binding.amountAddBtn.hideKeyboard()

                    val addedamount = binding.amountToAdd.text.toString().toFloat()
                    savedAmount += addedamount

                    textProgress = "R$ $savedAmount / R$ ${goalamount.toString()}"
                    binding.progressText.text = textProgress

                    val progressPercent = (savedAmount / goalamount * 100).toInt()
                    binding.determinateBar.progress = progressPercent



                }
                //Toast.makeText(activity,"Teste", Toast.LENGTH_SHORT).show()
            }


        }

        //Temporario


        return binding.root
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}