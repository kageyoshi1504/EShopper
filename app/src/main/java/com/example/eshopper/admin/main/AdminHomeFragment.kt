package com.example.eshopper.admin.main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.databinding.FragmentHomeAdminBinding
import com.google.firebase.auth.FirebaseAuth


class AdminHomeFragment : Fragment(R.layout.fragment_home_admin) {

    private lateinit var binding : FragmentHomeAdminBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var diaLog : LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding = FragmentHomeAdminBinding.inflate(layoutInflater)
      return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initActions()

    }

    private fun initView()
    {
        mAuth = FirebaseAuth.getInstance()
        diaLog = LoadingDialog(childFragmentManager)

    }

    private fun initActions()
    {
        binding.apply {

            order.setOnClickListener{
                findNavController().navigate(R.id.adminHomeToAdminOrder)
            }
            statistic.setOnClickListener{
                findNavController().navigate(R.id.adminHomeToAdminStatics)
            }
            product.setOnClickListener{
                findNavController().navigate(R.id.adminHomeToAdminProduct)
            }
            category.setOnClickListener{
                findNavController().navigate(R.id.adminHomeToAdminCategory)
            }
            logout.setOnClickListener{
                mAuth.signOut()
                findNavController().popBackStack()

            }

        }
    }

    }

