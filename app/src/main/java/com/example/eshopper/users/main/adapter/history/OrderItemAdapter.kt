package com.example.eshopper.users.main.adapter.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.OrderDetailItemBinding
import com.example.eshopper.databinding.OrderDetailProductBinding
import com.example.eshopper.users.main.model.Order

class OrderItemAdapter
    (private val orderList : ArrayList<Order>)
    :RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>()
{
    inner class OrderItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    {
        val binding = OrderDetailProductBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemAdapter.OrderItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_detail_product,parent,false)
        return OrderItemViewHolder(view)
    }



    override fun onBindViewHolder(holder: OrderItemAdapter.OrderItemViewHolder, position: Int) {
        val order = orderList[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(order.cartList[position].productImage)
                .into(image)
            tvPrice.text = Common.formatCurrency(order.cartList[position].productPrice)
            sumMoney.text = Common.formatCurrency(order.cartList[position].productPrice * order.cartList[position].amount)
            tvAmount.text = "x+${order.cartList[position].amount}"
            tvName.text = order.cartList[position].productName

        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}