package com.example.eshopper.users.main.model

import com.google.firebase.firestore.FirebaseFirestore

class Order(
    var uid : String = "",
    var name : String = "",
    var phone : String ="",
    var address : String ="",
    var orderTime : String = "",
    var orderDate : String ="",
    var status : Int = 0,
    var userId : String = "",
    var receiptDate : String = "",
    var receiptTime : String ="",
    var totalPrice : String ="",
    var cartList : MutableList<CartModel> = arrayListOf()
){
     fun update(userId :String , uid :String  , status : Int)
    {
        FirebaseFirestore.getInstance().collection("Users").document(userId)
            .collection("Order").document(uid)

            .update("status",status)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
}