package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentHomeBinding
import com.example.eshopper.users.main.adapter.home.HomeAdapter
import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.Event.Badge
import com.example.eshopper.users.main.model.Event.LoadData
import com.example.eshopper.users.main.model.ProductModel
import com.example.eshopper.users.main.utils.SampleData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var dialog: LoadingDialog
    private lateinit var fire: FirebaseFirestore
    private lateinit var mContext : Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        EventBus.getDefault().register(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
    }

    private fun initData()
    {
        dialog.show()
        // Danh sách danh mục

        // check login da
        val currentUSer =  FirebaseAuth.getInstance().currentUser
        if (currentUSer != null)
        {
            // Truyền data cho e hèm giỏ hàng
            fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection("Cart")
                .get()
                .addOnSuccessListener {
                        querySnapshot -> EventBus.getDefault().postSticky(Badge(querySnapshot.size()))
                }
            // Danh sách sản phẩm
            fire.collection("Product").get()
                .addOnSuccessListener {
                    val dataList = arrayListOf<ProductModel>()
                    for (productList in it)
                    {
                        val data = productList.toObject(ProductModel::class.java)
                        dataList.add(data)
                    }
                    SampleData.product = dataList
                }
                .addOnFailureListener{
                    it.message?.let { it1 -> Common.toast(mContext, it1) }
                }

            // Kiểu sản phẩm
            fire.collection("Type").get()
                .addOnSuccessListener {
                        querySnapshot ->

                    for (document in querySnapshot)
                    {

                        when (document.getString("id"))
                        {

                            "1" -> {
                                fire.collection("Product").whereEqualTo("type", "1")
                                    .get()
                                    .addOnSuccessListener{
                                            query ->
                                        val dataList = arrayListOf<ProductModel>()
                                        if (query != null) {
                                            for (hotProduct in query) {
                                                val data = hotProduct.toObject(ProductModel::class.java)
                                                dataList.add(data)
                                            }
                                        }
                                        SampleData.hotProduct = dataList
                                        binding.rvHome.adapter = HomeAdapter(SampleData.listView,onClick)
                                    }
                            }
                            "2" -> {
                                fire.collection("Product").
                                whereEqualTo("type","2")
                                    .get()
                                    .addOnSuccessListener{
                                            query  ->

                                        val dataList = arrayListOf<ProductModel>()
                                        if (query != null) {
                                            for (hotProduct in query){
                                                val data = hotProduct.toObject(ProductModel::class.java)
                                                dataList.add(data)
                                            }
                                        }
                                        dialog.safeDismiss()
                                        SampleData.popularProduct = dataList
                                        binding.rvHome.adapter = HomeAdapter(SampleData.listView,onClick)

                                    }



                            }
                        }


                    }
                }

        }


        fire.collection("Category").get()
            .addOnSuccessListener {
                    querySnapshot ->
                val dataList = arrayListOf<CategoryModel>()
                for (document in querySnapshot)
                {
                    val data = document.toObject(CategoryModel::class.java)
                    dataList.add(data)
                }
                SampleData.category = dataList

            }
            .addOnFailureListener{

            }

    }

    private val onClick: (itemId: String) -> Unit = {itemId->

        activity?.runOnUiThread {
            when(checkNumberOrText(itemId)) {
                false -> {
                    val action = HomeFragmentDirections.userToHomeCategoryDetail(itemId)
                    findNavController().navigate(action)
                }
                else -> {
                    val action = HomeFragmentDirections.userToHomeProductDetail(itemId)

                    findNavController().navigate(action)
                }
            }
        }

    }

    private fun checkNumberOrText(checkId : String) : Boolean
    {
        val numberPattern = Regex("\\d+")
        return numberPattern.matches(checkId)
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true)
    fun onTotalPriceChanged(event :LoadData){
        initData()

    }


 }
