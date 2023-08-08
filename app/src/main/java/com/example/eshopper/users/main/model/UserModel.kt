package com.example.eshopper.users.main.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserModel(
   val uid : String ="",
   val name : String = "",
   val avatar : String ="",
   val phone : String ="",
   val email : String ="",
   val userType : String ="",
   var orderNumber : Int = 0
)