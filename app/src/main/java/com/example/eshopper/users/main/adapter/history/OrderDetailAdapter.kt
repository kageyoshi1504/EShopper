package com.example.eshopper.users.main.adapter.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.databinding.OrderDetailBinding
import com.example.eshopper.users.main.model.Order

class OrderDetailAdapter (private val orderList : ArrayList<Order>)
    :RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    {
        val binding = OrderDetailBinding.bind(itemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.order_detail,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderDetailAdapter.ViewHolder, position: Int) {
       holder.binding.apply {
           name.text = orderList[position].name
           number.text = orderList[position].phone
           address.text = orderList[position].address
           setRecyclerView(rvDetail,orderList)
           valueUid.text = orderList[position].cartList[position].uid
           valueDateOrder.text = orderList[position].orderDate
           valueTimeOrder.text = orderList[position].orderTime
       }
    }
    private fun setRecyclerView(recyclerView: RecyclerView , orderItem : ArrayList<Order>)
    {
        val adapter = OrderItemAdapter(orderItem)
    }
    override fun getItemCount(): Int {
        return orderList.size
    }
}