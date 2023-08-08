package com.example.eshopper.users.main.model

class ProductModel(
    var uid: String = "",
    var productId : String ="",
    var productName : String ="",
    var productAmount : Long = 0L,
    var productPrice : Double = 0.0,
    var productImage : String ="",
    var type : String ="",
    var productDesc : String="",
    var categoryId: String ="",
    val categoryName: String=""
)
