package com.example.eshopper.admin.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.admin.main.adapter.category.AdminCategoryAdapter
import com.example.eshopper.common.ClickItem
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAdminCategoryBinding
import com.example.eshopper.users.main.model.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore


class AdminCategoryFragment : Fragment(R.layout.fragment_admin_category) {


    private  val binding : FragmentAdminCategoryBinding by viewBinding(FragmentAdminCategoryBinding::bind)
    private lateinit var dialog : LoadingDialog
    private lateinit var fire: FirebaseFirestore
    private lateinit var categoryList : ArrayList<CategoryModel>
    private lateinit var mContext : Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initAction()

    }

    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        categoryList = ArrayList()
        binding.emptyCategory.visibility = View.GONE
    }
    private fun initData()
    {
        dialog.show()
        fire.collection("Category"
        ).get()
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    for (document in it.result)
                    {
                        Common.toast(mContext,it.result.size().toString())
                        val categoryModel = document.toObject(CategoryModel::class.java)
                        categoryList.add(categoryModel)
                    }


                }
                dialog.safeDismiss()
                val adapter = AdminCategoryAdapter(mContext, categoryList)
                binding.apply {
                    rvCategory.adapter = adapter
                }

                adapter.setOnClick(object :ClickItem{
                    override fun onClick(position: Int) {

                        val categoryId = categoryList[position].categoryId
                        Log.d("categoryId",categoryId)
                        val action = AdminCategoryFragmentDirections.adminCategoryToAdminDetailCategory(categoryId)
                        findNavController().navigate(action)

                    }

                })
               if (categoryList.size <=0)
               {
                   binding.apply {
                       emptyCategory.visibility = View.VISIBLE
                       emptyCategory.text = "Danh mục rỗng , vui lòng thêm mới"
                       scroll.visibility = View.GONE
                   }

               }
            }

            .addOnFailureListener{
                dialog.safeDismiss()

            }

    }

    private fun initAction()
    {
        binding.apply {
            imageBack.setOnClickListener{
                findNavController().popBackStack()
            }
            imageAdd.setOnClickListener{
                findNavController().navigate(R.id.adminCategoryToAddCategory)
            }
        }

    }


}