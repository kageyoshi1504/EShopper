package com.example.eshopper.admin.main.adapter.category

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
import com.example.eshopper.users.main.model.CategoryModel

class AdminCategoryAdapter (
    private var context : Context,
    private var categoryList : ArrayList<CategoryModel>
        )
    :RecyclerView.Adapter<AdminCategoryAdapter.MyViewHolder>()
{
        inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
            var nameCategory : TextView
            var imageCategory : ImageView

            init{
                nameCategory  = itemView.findViewById(R.id.nameCategory) as TextView
                imageCategory = itemView.findViewById(R.id.imageCategory) as ImageView

                itemView.setOnClickListener(this)
            }

            override fun onClick(p0: View?) {
                clickItem.onClick(adapterPosition)
            }
        }

    private lateinit var clickItem : ClickItem
    fun setOnClick(clickItem: ClickItem)
    {
        this.clickItem = clickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_category_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.nameCategory.text = categoryList[position].categoryName
            Glide.with(context).load(categoryList[position].categoryImage).into(holder.imageCategory)

    }
}