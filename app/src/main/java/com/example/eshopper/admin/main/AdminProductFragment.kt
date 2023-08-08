package com.example.eshopper.admin.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.eshopper.R
import com.example.eshopper.admin.main.adapter.product.AdminProductAdapter
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAdminProductBinding
import com.example.eshopper.users.main.model.AllProduct
import com.example.eshopper.users.main.model.ProductModel
import com.google.firebase.firestore.FirebaseFirestore


class AdminProductFragment : Fragment(R.layout.fragment_admin_product) {

    private  val binding : FragmentAdminProductBinding by viewBinding(FragmentAdminProductBinding::bind)
    private lateinit var dialog: LoadingDialog
    private lateinit var fire : FirebaseFirestore
    private lateinit var allProduct : ArrayList<AllProduct>
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

    private fun initView() {

        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        allProduct = ArrayList()
        binding.emptyProduct.visibility = View.GONE
    }

    private fun initAction()
    {

        binding.apply {
            imageBack.setOnClickListener{
                findNavController().popBackStack()
            }
            imageAdd.setOnClickListener{
                findNavController().navigate(R.id.adminProductToAddProduct)
            }


        }
    }

    private fun initData()
    {
        dialog.show()
        fire.collection("Category").get()
            .addOnSuccessListener {
                    querySnapShot->
                for (document in querySnapShot){

                    val categoryId = document.getString("categoryId")
                    val categoryName = document.getString("categoryName")
                    fire.collection("Product").whereEqualTo("categoryId",categoryId)
                        .get()
                        .addOnSuccessListener{
                            query->
                            val productList = ArrayList<ProductModel>()
                            if (query != null) {

                                for (documents in query) {
                                    val product = documents.toObject(ProductModel::class.java)
                                    productList.add(product)
                                }
                                dialog.safeDismiss()
                                categoryName?.let { AllProduct(it,productList) }
                                    ?.let { allProduct.add(it) }
                                val adapter = AdminProductAdapter(mContext,allProduct,onProductClick)
                                binding.rvProduct.adapter = adapter


                            }





                            }
                            }

                        }

                        .addOnFailureListener{
                            dialog.safeDismiss()
                            Common.toast(mContext,"Loi roi ban ei")
                        }

                }


   private val onProductClick: (productId : String) -> Unit = { productId->
       val action = AdminProductFragmentDirections.adminProductToAdminDetailProduct(productId)
       findNavController().navigate(action)
   }

}
