package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentCartBinding
import com.example.eshopper.users.main.adapter.cart.CartAdapter
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Event.Badge
import com.example.eshopper.users.main.model.Event.EmptyCart
import com.example.eshopper.users.main.model.Event.Payment
import com.example.eshopper.users.main.model.Event.TotalPrice
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CartFragment : Fragment(R.layout.fragment_cart) {
    private val binding : FragmentCartBinding by viewBinding(FragmentCartBinding::bind)
    private lateinit var fire : FirebaseFirestore
    private lateinit var dialog : LoadingDialog
    private lateinit var mContext: Context
    private lateinit var adapter : CartAdapter
    private  var productId =""
    private lateinit var cartList : ArrayList<CartModel>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAction()
        initData()
        EventBus.getDefault().register(this)

    }

    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        cartList = ArrayList()
    }
    private fun initAction()
    {
        binding.apply {
            imageBack.setOnClickListener {
                findNavController().popBackStack()
            }

            payment.setOnClickListener {
                findNavController().navigate(R.id.cartToPayment)
            }
        }

    }

    // add gio hang cung voi user
    private fun initData()
    {
        dialog.show()
        fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid).collection("Cart")
            .get()
            .addOnSuccessListener {
                querySnapshot ->


                for (document in querySnapshot)
                {
                    productId = document.getString("productId").toString()
                    val data = document.toObject(CartModel::class.java)
                    cartList.add(data)
                }


                EventBus.getDefault().postSticky(Badge(cartList.size))
                EventBus.getDefault().postSticky(EmptyCart(cartList))
                EventBus.getDefault().postSticky(Payment(cartList))

                dialog.safeDismiss()

                 adapter = CartAdapter(cartList, onClick)
                if (cartList.isEmpty())
                {
                    binding.cardViewPayment.visibility = View.GONE

                }
                binding.money.text = Common.formatCurrency(adapter.totalPrice())
                binding.rvCart.adapter = adapter

            }
            .addOnFailureListener{

            }
    }

    private val onClick : (itemId : String) -> Unit = {itemId ->
        when(cartList.isEmpty())
        {
            true ->{
                findNavController().navigate(R.id.userFragment)
            }
            false ->{
                val action = CartFragmentDirections.cartToDetail(itemId)
                findNavController().navigate(action)
            }
        }

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
    fun onTotalPriceChanged(event : TotalPrice){
        val totalPrice = event.totalPrice
        binding.money.text = Common.formatCurrency(totalPrice)
        binding.cardViewPayment.isVisible = totalPrice != 0.0
        binding.cardViewPayment.isVisible = cartList.isNotEmpty()
    }
    @Subscribe(sticky = true)
    fun onCheckEmpty(event:EmptyCart)
    {
        binding.cardViewPayment.isVisible = cartList.isNotEmpty()
    }




}