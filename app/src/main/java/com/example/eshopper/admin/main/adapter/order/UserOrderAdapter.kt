package com.example.eshopper.admin.main.adapter.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.OrderItemBinding
import com.example.eshopper.users.main.model.Order
import com.example.eshopper.users.main.model.UserModel
import com.example.eshopper.users.main.utils.SampleData

class UserOrderAdapter (private val userList : ArrayList<UserModel> , private val onClick : (String) -> Unit):RecyclerView.Adapter<UserOrderAdapter.UserViewHolder>() {


    inner class UserViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    {
        val binding = OrderItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int  = userList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            uid.text = "Mã KH : ${userList[position].uid}"
            userName.text = "Tên khách hàng : ${userList[position].name}"
            amountOrder.text ="Số đơn đã đặt : ${userList[position].orderNumber}"
        }

        holder.itemView.setOnClickListener{
            onClick(userList[position].uid)
        }

    }




}