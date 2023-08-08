package com.example.eshopper.users.main.adapter.cart

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.databinding.CartItemBinding
import com.example.eshopper.databinding.EmptyCartBinding
import com.example.eshopper.users.main.model.CartModel
import com.example.eshopper.users.main.model.Event.EmptyCart
import com.example.eshopper.users.main.model.Event.Payment
import com.example.eshopper.users.main.model.Event.TotalPrice
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.greenrobot.eventbus.EventBus

class CartAdapter(  private val cartList : ArrayList<CartModel> , private val onClick : (String)-> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_CART_ITEM = 0
        const val VIEW_TYPE_EMPTY_CART = 1
    }

    inner class CartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = CartItemBinding.bind(itemView)
    }
    inner class EmptyCartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val binding = EmptyCartBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CART_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
            CartViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.empty_cart,parent,false)
            EmptyCartViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (cartList.isEmpty())
        {
            1
        }
        else {

            cartList.size
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder)
        {
            is CartViewHolder -> {
                holder.binding.apply {
                    Glide.with(holder.itemView.context).load(cartList[position].productImage).into(image)
                    tvName.text = cartList[position].productName
                    tvPrice.text = Common.formatCurrency(cartList[position].productPrice)
                    amount.text = cartList[position].amount.toString()

                    // Click vào image để đến với productDetail
                    image.setOnClickListener {
                        onClick(cartList[position].productId)
                    }

                    if (cartList[position].amount == 0L)
                    {
                        minus.setOnClickListener {
                            return@setOnClickListener
                        }
                        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                            .collection("Cart").document(cartList[position].uid).delete()
                            .addOnSuccessListener {
                                cartList.remove(cartList[position])
                                notifyDataSetChanged()

                            }

                    }
                    else if (cartList[position].amount < cartList[position].productAmount && cartList[position].amount >= 0 )
                    {
                        // Thêm giảm sản phẩm trong giỏ hàng
                        minus.setOnClickListener {

                            cartList[position].amount--
                            cartList[position].updateAmount(cartList[position])
                            notifyItemChanged(position)
                            EventBus.getDefault().postSticky(TotalPrice(totalPrice()))

                        }
                        add.setOnClickListener {

                            cartList[position].amount++
                            cartList[position].updateAmount(cartList[position])
                            notifyItemChanged(position)
                            EventBus.getDefault().postSticky(TotalPrice(totalPrice()))

                        }
                    }
                    else if (cartList[position].amount == cartList[position].productAmount)
                    {
                        error.visibility = View.VISIBLE
                        error.text = "Vuợt quá số lượng sản phẩm có trong cửa hàng"
                        add.setOnClickListener {
                            return@setOnClickListener
                        }

                    }



                    delete.setOnClickListener {
                        FirebaseFirestore.getInstance().collection("Users")
                                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                                    .collection("Cart")
                                    .document(cartList[position].uid)
                                    .delete()
                                    .addOnCompleteListener {
                                            task ->
                                        if (task.isSuccessful)
                                        {
                                            cartList.remove(cartList[position])
                                            notifyDataSetChanged()
                                            Log.d("cart_delete",task.exception.toString())

                                        }
                                        else {
                                           Log.e("cart_delete", task.exception.toString())
                                        }
                                        EventBus.getDefault().postSticky(Payment(cartList))
                                        EventBus.getDefault().postSticky(TotalPrice(totalPrice()))
                            }

                    }


                    image.setOnClickListener {
                        onClick(cartList[position].productId)
                    }
                }
            }
            is EmptyCartViewHolder->{
                holder.binding.backHome.setOnClickListener{
                    onClick("")
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(cartList.isEmpty())
        {
            true -> VIEW_TYPE_EMPTY_CART
            false -> VIEW_TYPE_CART_ITEM
        }
    }

     fun totalPrice() : Double
    {
        var totalPrice = 0.0
        if (cartList.isEmpty())
        {
            totalPrice = 0.0
        }
        else {
            for (cart in cartList)
            {
                totalPrice += cart.amount * cart.productPrice.toLong()
            }
        }


        return totalPrice
    }


}