package com.example.eshopper.admin.main.adapter.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.common.ClickItem
import com.example.eshopper.users.main.model.AllProduct
import com.example.eshopper.users.main.model.ProductModel

class AdminProductAdapter(
    private val context : Context,
    private val allProduct : List<AllProduct>,
    private val onProductClick : (String)->Unit

 ) :RecyclerView.Adapter<AdminProductAdapter.ParentViewHolder>()
{

     inner class ParentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
     {
         var categoryItem : TextView
         var itemRecyclerView : RecyclerView
         init {
             categoryItem = itemView.findViewById(R.id.categoryTitle) as TextView
             itemRecyclerView = itemView.findViewById(R.id.rv_parent_product) as RecyclerView
         }
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_parent_product,parent,false))
    }

    override fun getItemCount() = allProduct.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.apply {
             categoryItem.text = allProduct[position].categoryTitle
            setProductItemRecyclerView(itemRecyclerView,allProduct[position].allProduct)
        }
    }

    private fun setProductItemRecyclerView(recyclerView: RecyclerView,productItem : List<ProductModel>)
    {
        val adapter = ProductItemAdapter(productItem,onProductClick,context)
        recyclerView.adapter = adapter

    }
}
