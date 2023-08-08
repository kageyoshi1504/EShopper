package com.example.eshopper.admin.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.eshopper.R
import com.example.eshopper.admin.main.adapter.order.OrderAdapter
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentOrderDetailBinding
import com.example.eshopper.users.main.model.Order
import com.google.firebase.firestore.FirebaseFirestore

class OrderDetailFragment : Fragment(R.layout.fragment_order_detail) {
    private val binding : FragmentOrderDetailBinding by viewBinding(FragmentOrderDetailBinding::bind)

    private lateinit var  mContext : Context
    private lateinit var dialog : LoadingDialog
    private lateinit var fire : FirebaseFirestore
    private  val args : OrderDetailFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
        initData()
    }


    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()

    }

    private fun initAction()
    {

    }

    private fun initData(){
        fire.collection("Users")
            .document(args.uid)
            .collection("Order")
            .addSnapshotListener{
                querySnapshot , exception ->
                if (exception != null)
                {
                    Log.d("TAG","Listen failed : $exception")
                }
                val orderList = ArrayList<Order>()
                if (querySnapshot!=null)
                {
                    for (document in querySnapshot)
                    {
                        val data = document.toObject(Order::class.java)
                        orderList.add(data)
                    }

                    binding.rvOrderDetail.adapter = OrderAdapter(orderList)
                }
            }

    }




}