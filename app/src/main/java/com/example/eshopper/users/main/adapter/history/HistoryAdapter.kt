package com.example.eshopper.users.main.adapter.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.databinding.HistoryItemBinding
import com.example.eshopper.users.main.model.Order

class HistoryAdapter( private var historyList : ArrayList<Order>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>()
{
    inner class HistoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        val binding = HistoryItemBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item,parent,false)
       return HistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.apply {
            uid.text="Mã : ${historyList[position].uid}"
            amount.text = "Số lượng : ${historyList[position].cartList.size}"
            date.text = "Ngày đặt : ${historyList[position].orderDate}"
            address.text = "Địa chỉ : ${historyList[position].address}"
            time.text = historyList[position].orderTime
            total.text = "Thanh toán :${historyList[position].totalPrice}"
           when (historyList[position].status)
           {
               1 -> {
                   status.setTextColor(holder.itemView.context.getColor(R.color.tint_secondary))
                    status.text ="Đang chờ xác nhận"
               }
               2 -> {
                   status.setTextColor(holder.itemView.context.getColor(R.color.tint_primary))
                   status.text = "Đang giao hàng"

               }
               3 -> {
                   status.setTextColor(holder.itemView.context.getColor(R.color.green))
                   status.text = "Giao hàng thành công"

               }
               4 ->{
                   status.setTextColor(holder.itemView.context.getColor(R.color.red))
                   status.text = "Đơn hàng đã hủy"

               }
           }
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

}


