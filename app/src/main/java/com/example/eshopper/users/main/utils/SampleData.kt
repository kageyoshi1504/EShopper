package com.example.eshopper.users.main.utils

import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.Order
import com.example.eshopper.users.main.model.ProductModel
import com.example.eshopper.users.main.model.UserModel

object SampleData {
    var category = arrayListOf<CategoryModel>()

    var user = arrayListOf<UserModel>()

    var product = arrayListOf<ProductModel>()

    var hotProduct = arrayListOf<ProductModel>()

    var popularProduct = arrayListOf<ProductModel>()

    var historyList = arrayListOf<Order>()

    var amountOrder = 0
    val listView = listOf<Int>(
        1,2,3,4
    )
}