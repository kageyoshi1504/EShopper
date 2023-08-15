package com.example.eshopper.users.main.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.databinding.CategoryParentItemBinding
import com.example.eshopper.databinding.HomeCategoryListBinding
import com.example.eshopper.databinding.HomeCategoryListItemBinding
import com.example.eshopper.databinding.ItemCategoryBinding
import com.example.eshopper.users.main.model.CategoryModel

class CategoryListAdapter (private val dataList : ArrayList<CategoryModel> , private val onClick : (String) -> Unit)  : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = HomeCategoryListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_category_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.apply {
                Glide.with(holder.itemView).load(dataList[position].categoryImage).into(imageCategory)
                nameCategory.text = dataList[position].categoryName
            }

            holder.binding.imageCategory.setOnClickListener{
                onClick(dataList[position].categoryName)
            }
    }

}