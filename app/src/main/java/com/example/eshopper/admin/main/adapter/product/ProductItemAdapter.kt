package com.example.eshopper.admin.main.adapter.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.ClickItem
import com.example.eshopper.common.Common
import com.example.eshopper.users.main.model.ProductModel

open class ProductItemAdapter(
    private val productList: List<ProductModel>,
    private val onProductClick: (String)->Unit,
    private val context: Context
) :
    RecyclerView.Adapter<ProductItemAdapter.ChildHolder>() {


    inner class ChildHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    {
        var nameProduct : TextView
        var priceProduct : TextView
        var imageProduct : ImageView
        var amountProduct : TextView
        init{
            nameProduct = itemView.findViewById(R.id.nameProduct) as TextView
            priceProduct = itemView.findViewById(R.id.priceProduct) as TextView
            imageProduct = itemView.findViewById(R.id.imageProduct) as ImageView
            amountProduct = itemView.findViewById(R.id.amountProduct) as TextView

        }



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.admin_product_item,parent,false)
        return ChildHolder(view)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        holder.apply {
            nameProduct.text = productList[position].productName
            priceProduct.text = Common.formatCurrency(productList[position].productPrice)
            amountProduct.text = productList[position].productAmount.toString()
            Glide.with(context).load(productList[position].productImage).into(imageProduct)
        }
        holder.itemView.setOnClickListener{
            onProductClick(productList[position].productId)
        }

    }
}