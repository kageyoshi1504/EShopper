package com.example.eshopper.users.main.adapter.category

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
        private var context : Context ,
        private var categoryList: List<String>
     )

    : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}