package com.example.eshopper.users.main

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentPaymentBinding
import com.example.eshopper.users.main.adapter.payment.PaymentAdapter
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Event.Payment
import com.example.eshopper.users.main.model.Event.StatusPayment
import com.example.eshopper.users.main.model.Order
import com.example.eshopper.users.main.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.text.DateFormat
import java.util.Calendar
import java.util.UUID

class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding: FragmentPaymentBinding by viewBinding(FragmentPaymentBinding::bind)
    private lateinit var dialog: LoadingDialog
    private lateinit var mContext: Context
    private lateinit var fire : FirebaseFirestore
   private lateinit var paymentList :MutableList<CartModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
        EventBus.getDefault().register(this)


    }

    private fun initView() {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        binding.methodEt.setText("Tiền mặt")
    }

    private fun initAction() {
        binding.apply {
            imageBack.setOnClickListener {
                findNavController().popBackStack()
            }
            order.setOnClickListener {
                if (validate())
                {

                    dialog.show()
                    val uid = Common.getId()
                    fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .collection("Order").document(uid)
                        .set(
                            Order(
                                uid = uid,
                                name = binding.nameEt.text.toString(),
                                phone = binding.phoneEt.text.toString(),
                                address = binding.addressEt.text.toString(),
                                totalPrice = binding.totalPrice.text.toString(),


                                receiptDate = "",
                                receiptTime = "",

                                orderDate = Common.getCurrentDate(),
                                orderTime = Common.getCurrentTime(),

                                status = 1,

                                cartList = paymentList,
                                userId = FirebaseAuth.getInstance().currentUser!!.uid
                        )
                        )
                        .addOnSuccessListener {
                            dialog.safeDismiss()
                            Common.toast(mContext , "Đặt hàng thành công")
                            fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                                .collection("Cart").get()
                                .addOnSuccessListener { querySnapshot ->
                                    for (document in querySnapshot) {
                                        document.reference.delete()
                                    }
                                    findNavController().navigate(R.id.action_paymentFragment_to_successPayment)
                                    fire.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                                        .collection("Order")
                                        .get()
                                        .addOnSuccessListener {
                                                querySnapshots ->
                                            val update = HashMap<String,Any>()
                                            update["orderNumber"] = querySnapshots.size()
                                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)
                                                .updateChildren(
                                                    update
                                                )
                                        }
                                }
                                .addOnFailureListener{
                                    it.message?.let { it1 -> Log.e("Failed", it1) }
                                }


                        }
                        .addOnFailureListener{
                            it.message?.let { it1 -> Common.toast(mContext, it1) }
                        }


                }
            }

        }



    }
    private fun validate() :Boolean{

        val name = binding.nameEt.text.toString()
        val phone = binding.phoneEt.text.toString()
        val address = binding.addressEt.text.toString()
       if (TextUtils.isEmpty(name)){
           Common.toast(mContext,"Vui lòng nhập họ và tên")
           return false
       }
        if (TextUtils.isEmpty(phone))
        {
            Common.toast(mContext,"Vui lòng nhập điện thoại")
            return false
        }
        if (TextUtils.isEmpty(address))
        {
            Common.toast(mContext,"Vui lòng nhập địa chỉ")
            return false
        }
        return true
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
    fun onData(event: Payment)
    {
        paymentList = event.paymentList
        val adapter = PaymentAdapter(paymentList)
        binding.rvPayment.adapter = adapter
        binding.totalPrice.text = Common.formatCurrency(adapter.totalPrice())
    }






}