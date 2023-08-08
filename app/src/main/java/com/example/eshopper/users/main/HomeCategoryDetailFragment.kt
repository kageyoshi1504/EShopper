package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentHomeCategoryDetailBinding
import com.example.eshopper.users.main.adapter.details.HomeCategoryDetailAdapter
import com.example.eshopper.users.main.model.ProductModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeCategoryDetailFragment : Fragment(R.layout.fragment_home_category_detail) {

    private val binding : FragmentHomeCategoryDetailBinding by viewBinding(FragmentHomeCategoryDetailBinding::bind)
    private lateinit var mContext : Context
    private val args : HomeCategoryDetailFragmentArgs by navArgs()
    private lateinit var fire : FirebaseFirestore
    private lateinit var dialog: LoadingDialog

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
            fire = FirebaseFirestore.getInstance()
            dialog = LoadingDialog(childFragmentManager)
    }

    private fun initAction()
    {
        binding.imageBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun initData()
    {
        dialog.show()
        fire.collection("Product").whereEqualTo("categoryName",args.itemId)
            .get()
            .addOnSuccessListener {
                querySnapshot ->
                val detailList = arrayListOf<ProductModel>()
                for (document in querySnapshot)
                {
                    val data = document.toObject(ProductModel::class.java)

                    detailList.add(data)

                }
                dialog.safeDismiss()
                binding.title.text = args.itemId
                val adapter = HomeCategoryDetailAdapter(detailList, onClick)
                binding.rvCategoryDetail.adapter = adapter

            }
    }
    private val onClick : (itemId : String) -> Unit = { itemId ->
        val action = HomeCategoryDetailFragmentDirections.homeCategoryDetailToHomeProductDetail(itemId)
        findNavController().navigate(action)

    }


}