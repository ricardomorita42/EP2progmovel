package com.example.myfinancialapp

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myfinancialapp.databinding.FragmentTitleBinding
import android.app.Activity





class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.financialButton.setOnClickListener {v:View ->
            v.findNavController().navigate(R.id.action_titleFragment_to_financesFragment)
        }

        binding.loginButton.setOnClickListener { v:View ->
            v.findNavController().navigate(R.id.action_titleFragment_to_loginFragment)
        }

        binding.weatherButton.setOnClickListener { v:View ->
            v.findNavController().navigate(R.id.action_titleFragment_to_weatherFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}