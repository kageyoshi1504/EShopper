package com.example.eshopper.users.main

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.viewBinding

import com.example.eshopper.databinding.PaymentSuccessBinding

class SuccessPayment : Fragment(R.layout.payment_success) {

    private val binding : PaymentSuccessBinding by viewBinding(PaymentSuccessBinding ::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backHome.setOnClickListener{
            findNavController().navigate(R.id.userFragment)
        }
    }


}