package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentHomeProductDetailBinding
import com.example.eshopper.users.main.adapter.cart.CartAdapter
import com.example.eshopper.users.main.adapter.details.HomeProductDetailAdapter
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Event.Badge
import com.example.eshopper.users.main.model.Event.EmptyCart
import com.example.eshopper.users.main.model.Event.Payment
import com.example.eshopper.users.main.model.ProductModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.UUID


class HomeProductDetailFragment : Fragment(R.layout.fragment_home_product_detail) {
    private val binding: FragmentHomeProductDetailBinding by viewBinding(
        FragmentHomeProductDetailBinding::bind
    )
    private val args: HomeProductDetailFragmentArgs by navArgs()
    private lateinit var dialog: LoadingDialog
    private lateinit var mContext: Context
    private lateinit var fire: FirebaseFirestore
    private lateinit var detailList : ArrayList<ProductModel>

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

    private fun initView() {
        fire = FirebaseFirestore.getInstance()
        dialog = LoadingDialog(childFragmentManager)
    }

    private fun initAction() {
        binding.apply {

            imageBack.setOnClickListener {
                findNavController().popBackStack()
            }
            layoutAddToCart.setOnClickListener {
                addCart()
            }
            order.setOnClickListener {
                findNavController().navigate(R.id.productToPayment)
            }

        }
    }

    private fun initData(){
        dialog.show()
        fire.collection("Product").whereEqualTo("productId",args.itemId)
            .get()
            .addOnSuccessListener {
                querySnapshot ->
                val get = HashMap<String,Any>()
                 detailList = arrayListOf()
                for (document in querySnapshot)
                {
                    val data = document.toObject(ProductModel::class.java)
                    detailList.add(data)
                }
                dialog.safeDismiss()
                binding.title.text = "Chi tiết sản phẩm"

                val adapter = HomeProductDetailAdapter(detailList)
                binding.rvProductDetail.adapter = adapter
            }
            .addOnFailureListener{
                it.message?.let { it1 -> Common.toast(mContext, it1) }
            }

        fire.collection("Product").whereEqualTo("productId",args.itemId)
            .get().addOnSuccessListener { querySnapshots ->
                for (document in querySnapshots) {
                    val data = document.toObject(ProductModel::class.java)
                    EventBus.getDefault().postSticky(Payment(arrayListOf(CartModel(
                        productName = data.productName,
                        productPrice = data.productPrice,
                        productImage = data.productImage,
                        productAmount = data.productAmount,
                        productId = args.itemId,
                        amount = 1,


                    ))))
                }

            }


    }
    private fun addCart()
    {
        val uid = FirebaseAuth.getInstance().uid!!
        fire.collection("Users").document(uid).collection("Cart")
            .whereEqualTo("productId",args.itemId).get()
            .addOnSuccessListener {
                    querySnapshot ->

                if (querySnapshot.size() != 0)
                {
                    if (querySnapshot.size() > 0)
                    {
                        for (document in querySnapshot)
                        {
                            var amount : Long? = document.getLong("amount")
                            if (amount != null) {
                                if (amount > 0) {
                                    amount += 1
                                }
                                fire.collection("Users").document(uid)
                                    .collection("Cart")
                                    .document(document.id)
                                    .update("amount",amount)
                                    .addOnSuccessListener {
                                        Common.toast(mContext,"Đã thêm vào giỏ hàng")
                                      fire.collection("Users").document(uid)
                                          .collection("Cart").get()
                                          .addOnSuccessListener { querySnapshot ->
                                              EventBus.getDefault().postSticky(Badge(querySnapshot.size()))


                                          }

                                    }
                                    .addOnFailureListener{

                                    }
                            }
                        }
                    }
                }
                else{
                    val addCart = HashMap<String,Any>()
                    fire.collection("Product").whereEqualTo("productId",args.itemId)
                        .get().addOnSuccessListener {
                            querySnapshots ->
                            for (document in querySnapshots){
                                val data = document.toObject(ProductModel::class.java)
                                addCart["uid"] = UUID.randomUUID().toString()
                                addCart["productId"] = args.itemId
                                addCart["productImage"] = data.productImage
                                addCart["productName"] =data.productName
                                addCart["productPrice"] = data.productPrice
                                addCart["productAmount"] = data.productAmount
                                addCart["amount"] = 1
                                addCart["userId"] = uid
                            }
                            fire.collection("Users")
                                .document(uid).collection("Cart").document(addCart["uid"].toString())
                                .set(addCart)
                                .addOnSuccessListener {
                                    Common.toast(mContext,"Đã thêm vào giỏ hàng")
                                }

                                .addOnFailureListener{
                                    Common.toast(mContext,"Chưa được thêm vào giỏ hàng")
                                }
                        }
                        .addOnFailureListener{

                        }


                }
            }
    }


}