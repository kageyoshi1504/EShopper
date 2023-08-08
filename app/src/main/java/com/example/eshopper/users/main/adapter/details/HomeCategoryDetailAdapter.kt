package com.example.eshopper.users.main.adapter.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HomeCategoryDetailItemBinding
import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.ProductModel
import com.example.eshopper.users.main.utils.SampleData

class HomeCategoryDetailAdapter(private val detailList : ArrayList<ProductModel>, private val onClick : (String)-> Unit) : RecyclerView.Adapter<HomeCategoryDetailAdapter.HomeCategoryDetailViewHolder>() {

 inner  class HomeCategoryDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
 {
     val binding = HomeCategoryDetailItemBinding.bind(itemView)
 }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCategoryDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_category_detail_item,parent,false)
        return HomeCategoryDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    override fun onBindViewHolder(holder: HomeCategoryDetailViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(detailList[position].productImage).into(imageProduct)
            nameProduct.text = detailList[position].productName
            priceProduct.text = Common.formatCurrency(detailList[position].productPrice)
        }
        holder.itemView.setOnClickListener{
            onClick(detailList[position].productId)
        }
    }


}