package com.example.eshopper.users.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentHistoryBinding
import com.example.eshopper.users.main.adapter.history.FragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val binding : FragmentHistoryBinding by viewBinding(FragmentHistoryBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }


fun initView()
{
    val tabLayout = binding.tabLayout
    val viewPager2 = binding.viewPager2

    viewPager2.adapter = FragmentAdapter(childFragmentManager , lifecycle)
    TabLayoutMediator(
        tabLayout,viewPager2)
    {
            tab , position ->
        when(position)
        {
            0 ->{
                tab.text = "Chờ xác nhận"
            }
            1 ->{
                tab.text = "Đang giao"
            }
            2->{
                tab.text = "Đã nhận"
            }
            else -> {
                tab.text ="Đã hủy"
            }
        }

    }.attach()


}

fun initAction()
{
    binding.imageBack.setOnClickListener {
        findNavController().popBackStack()
    }
}
}