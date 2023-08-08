package com.example.eshopper.users.main.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HomeHotDealProductItemBinding
import com.example.eshopper.users.main.model.ProductModel

class HotProductAdapter(private val hotProductList : ArrayList<ProductModel>, private  val onProductClick:(String)->Unit) : RecyclerView.Adapter<HotProductAdapter.HotProductViewHolder>() {

    inner class HotProductViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val binding = HomeHotDealProductItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_hot_deal_product_item,parent,false)
        return HotProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hotProductList.size
    }

    override fun onBindViewHolder(holder: HotProductViewHolder, position: Int) {
        holder.binding.apply {
          Glide.with(holder.itemView.context).load(hotProductList[position].productImage).into(imageProduct)
          nameProduct.text = hotProductList[position].productName
          priceProduct.text = Common.formatCurrency(hotProductList[position].productPrice)

        }

        holder.itemView.setOnClickListener{
            onProductClick(hotProductList[position].productId)
        }
    }


}