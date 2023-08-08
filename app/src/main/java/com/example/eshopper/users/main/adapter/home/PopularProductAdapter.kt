package com.example.eshopper.users.main.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HomePopularProductItemBinding
import com.example.eshopper.users.main.model.ProductModel

class PopularProductAdapter(private val popularProduct : ArrayList<ProductModel>,private val onProductClick: (String) -> Unit) : RecyclerView.Adapter<PopularProductAdapter.PopularProductViewHolder>() {
    
    inner class PopularProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = HomePopularProductItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularProductViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.home_popular_product_item,parent,false)
        return PopularProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularProduct.size
    }

    override fun onBindViewHolder(holder: PopularProductViewHolder, position: Int) {
       holder.binding.apply {
           Glide.with(holder.itemView.context).load(popularProduct[position].productImage).into(imageProduct)
           nameProduct.text = popularProduct[position].productName
           priceProduct.text = Common.formatCurrency(popularProduct[position].productPrice)

       }

        holder.itemView.setOnClickListener{
            onProductClick(popularProduct[position].productId)
        }
    }

}