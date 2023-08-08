package com.example.eshopper.users.main.adapter.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.PaymentItemBinding
import com.example.eshopper.databinding.PaymentSuccessBinding
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Event.StatusPayment
import com.example.eshopper.users.main.model.Event.TotalPrice
import org.greenrobot.eventbus.EventBus

class PaymentAdapter(private var dataList: MutableList<CartModel>)  :RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    inner class PaymentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        val binding = PaymentItemBinding.bind(itemView)
    }
    inner class PaymentSuccessViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = PaymentSuccessBinding.bind(itemView)
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PAYMENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_item,parent,false)
            PaymentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_success,parent,false)
            PaymentSuccessViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(dataList.isEmpty())
        {
            true -> PAYMENT_SUCCESS
            false -> PAYMENT
        }
    }
    override fun getItemCount(): Int {
      return if (dataList.isEmpty())
      {
          1
      }
        else {
                dataList.size
        }
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is PaymentViewHolder ->{
                holder.binding.apply {
                    tvName.text = dataList[position].productName
                    Glide.with(holder.itemView.context).load(dataList[position].productImage).into(image)
                    tvPrice.text = Common.formatCurrency(dataList[position].productPrice)
                    tvAmount.text = "x"+dataList[position].amount.toString()
                    sumMoney.text = Common.formatCurrency(dataList[position].productPrice * dataList[position].amount)
                    EventBus.getDefault().postSticky(TotalPrice(
                        dataList[position].productPrice * dataList[position].amount
                    ))

                }

            }
            is PaymentSuccessViewHolder -> {

            }
        }
    }

    fun totalPrice() : Double
    {
        var totalPrice = 0.0
        if (dataList.isEmpty())
        {
            totalPrice = 0.0
        }
        else {
            for (cart in dataList)
            {
                totalPrice += cart.amount * cart.productPrice.toLong()
            }
        }

        return totalPrice
    }

    companion object{
        const val PAYMENT = 0
        const val PAYMENT_SUCCESS = 1

    }
}