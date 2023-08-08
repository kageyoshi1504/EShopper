package com.example.eshopper.users.main.adapter.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HomeProductDetailItemBinding
import com.example.eshopper.users.main.model.ProductModel

class HomeProductDetailAdapter(private  var detailList : ArrayList<ProductModel>)  : RecyclerView.Adapter<HomeProductDetailAdapter.HomeProductDetailViewHolder>(){

    inner class HomeProductDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = HomeProductDetailItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_product_detail_item,parent,false)
        return HomeProductDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
       return detailList.size
    }

    override fun onBindViewHolder(holder: HomeProductDetailViewHolder, position: Int) {
        val product = detailList[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(product.productImage).into(productImage)
            productName.text = product.productName
            productPrice.text = Common.formatCurrency(product.productPrice)
            productDesc.text = product.productDesc
        }

    }


}