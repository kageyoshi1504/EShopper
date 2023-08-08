package com.example.eshopper.admin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.eshopper.R
import com.example.eshopper.databinding.FragmentAdminStaticsBinding

class AdminStaticsFragment : Fragment() {

    private lateinit var binding : FragmentAdminStaticsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentAdminStaticsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }
    private fun initAction()
    {
        binding.imageBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}