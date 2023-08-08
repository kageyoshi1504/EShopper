package com.example.eshopper.admin.main


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.admin.main.adapter.order.UserOrderAdapter
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAdminOrderBinding
import com.example.eshopper.users.main.model.Order
import com.example.eshopper.users.main.model.UserModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class AdminOrderFragment : Fragment(R.layout.fragment_admin_order) {
   private val binding : FragmentAdminOrderBinding by viewBinding(FragmentAdminOrderBinding::bind)
   private lateinit var mContext : Context
   private lateinit var fire : FirebaseFirestore
   private lateinit var dialog: LoadingDialog
   private lateinit var adapter : UserOrderAdapter
   private lateinit var db : DatabaseReference



   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      initView()
      initAction()
      initData()


   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      mContext = context
   }
   private fun initView()
   {
      fire = FirebaseFirestore.getInstance()
      dialog = LoadingDialog(childFragmentManager)
      db = FirebaseDatabase.getInstance().getReference("Users")
   }

   private fun initAction(){
      binding.apply {
         imageBack.setOnClickListener {
            findNavController().popBackStack()
         }
      }

   }


   private fun initData()
   {
      //dialog.show()
      db.addListenerForSingleValueEvent(object : ValueEventListener{
         override fun onDataChange(snapshot: DataSnapshot) {

            val userIds = arrayListOf<String>()
            val userList  = ArrayList<UserModel>()
            for (accountSnap in snapshot.children)
            {
               val account = accountSnap.getValue(UserModel::class.java)
               if (account != null) {
                  if (account.userType =="user")
                  {
                     userList.add(account)
                     account.uid.let{
                        //userIds.add(it.toString())

                        it.let{ uid -> userIds.add(uid)}

                     }
                  }
               }

            }

            if (userIds.isNotEmpty()) {

               for (userId in userIds) {

                  fire.collection("Users")
                     .document(userId)
                     .collection("Order")
                     .get()
                     .addOnSuccessListener{querySnapshot ->

                        if (querySnapshot != null)
                        {
                           val orderList = arrayListOf<Order>()

                          for (document in querySnapshot)
                          {
                             val data = document.toObject(Order::class.java)
                             orderList.add(data)

                          }
                           adapter = UserOrderAdapter(userList , onClick)
                           binding.rvOrder.adapter = adapter



                        }
                     }
               }

            }
         }

         override fun onCancelled(error: DatabaseError) {

         }

      })

   }

   private val onClick : (uid : String) -> Unit = {uid->
      val action = AdminOrderFragmentDirections.actionAdminOrderFragmentToOrderDetailFragment(uid)
      findNavController().navigate(action)

   }
}