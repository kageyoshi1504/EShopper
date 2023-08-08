package com.example.eshopper.admin.main.adapter.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.common.ClickItem
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.HistoryItemBinding
import com.example.eshopper.databinding.OrderDetailItemBinding
import com.example.eshopper.databinding.OrderItemBinding
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Order
import com.example.eshopper.users.main.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class OrderAdapter(private var orderList : ArrayList<Order>) : RecyclerView.Adapter<OrderAdapter.HistoryViewHolder>()
{

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = OrderDetailItemBinding.bind(itemView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_detail_item,parent,false)
        return HistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.apply {
            uid.text = "Mã : ${orderList[position].uid}"
            amountOrder.text= "Số đơn đã đặt : ${orderList[position].cartList.size}"
            when (orderList[position].status)
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
            edit.setOnClickListener{
               showPopupMenu(it , orderList[position])
            }




        }
    }
    override fun getItemCount(): Int {
        return orderList.size
    }

    private fun showPopupMenu(anchorView: View, item: Order) {
        val popupMenu = PopupMenu(anchorView.context, anchorView)
        popupMenu.inflate(R.menu.choice) // Your popup menu XML file


        popupMenu.setOnMenuItemClickListener {
                items ->
            when (items.itemId)
            {
                R.id.confirm -> {
                  item.update(item.userId,item.uid,2)
                }

                R.id.cancel ->{
                   item.update(item.userId,item.uid,4)

                }
            }
            true

        }
        if (item.status == 2 || item.status == 4)
        {
            popupMenu.dismiss()
        }
        else {
            popupMenu.show()
        }

    }




}
