package com.example.eshopper.users.main.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class
CartModel(
    val uid : String = "",
    val productId : String ="",
    val productImage : String ="",
    val productName : String ="",
    var productPrice : Double =0.0,
    var amount : Long = 0L,
    var productAmount : Long = 0L

    )

{
    fun updateAmount(cartList : CartModel)
    {

        val db = FirebaseFirestore.getInstance()
        db.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("Cart").document(cartList.uid).update("amount",cartList.amount)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }


}