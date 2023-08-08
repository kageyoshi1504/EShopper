package com.example.eshopper.users.main.adapter.history

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eshopper.users.main.CancelledFragment
import com.example.eshopper.users.main.HistoryFragment
import com.example.eshopper.users.main.InDeliveryFragment
import com.example.eshopper.users.main.PendingFragment
import com.example.eshopper.users.main.ReceiveFragment


class FragmentAdapter(fm : FragmentManager,lifecycle: Lifecycle)  :FragmentStateAdapter(fm,lifecycle){


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return when(position)
        {
            0 -> PendingFragment()
            1 -> InDeliveryFragment()
            2 -> ReceiveFragment()
            else ->{
                CancelledFragment()
            }
        }


}



}