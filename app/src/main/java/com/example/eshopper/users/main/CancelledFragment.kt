package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.eshopper.R
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentCancelledBinding
import com.example.eshopper.users.main.adapter.history.HistoryAdapter
import com.example.eshopper.users.main.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CancelledFragment : Fragment(R.layout.fragment_cancelled) {

    private val binding : FragmentCancelledBinding by viewBinding(FragmentCancelledBinding::bind)
    private lateinit var mContext : Context
    private lateinit var dialog: LoadingDialog
    private lateinit var fire : FirebaseFirestore
    private lateinit var adapter : HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    fun initView()
    {

        fire = FirebaseFirestore.getInstance()
        dialog = LoadingDialog(childFragmentManager)


    }

    fun initData() {
        dialog.show()
        fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("Order")
            .whereEqualTo("status",4)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val cancelList = arrayListOf<Order>()
                for (document in querySnapshot) {
                    val data = document.toObject(Order::class.java)
                    cancelList.add(data)
                }
                adapter = HistoryAdapter(cancelList)
                binding.rvCancle.adapter = adapter

                dialog.safeDismiss()
            }
    }

    private val onClick : (uid : String) -> Unit ={

    }

}