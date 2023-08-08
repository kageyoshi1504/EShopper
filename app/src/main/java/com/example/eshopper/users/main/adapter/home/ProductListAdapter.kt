package com.example.eshopper.users.main.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HomeProductListItemBinding
import com.example.eshopper.users.main.model.ProductModel



class ProductListAdapter(private val productList : ArrayList<ProductModel>, private val onProductClick:(String)->Unit) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = HomeProductListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.home_product_list_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
                Glide.with(holder.itemView.context).load(productList[position].productImage).into(imageProduct)
                nameProduct.text = productList[position].productName
                priceProduct.text = Common.formatCurrency(productList[position].productPrice)
        }

        holder.itemView.setOnClickListener{
            onProductClick(productList[position].productId)
        }
    }
}