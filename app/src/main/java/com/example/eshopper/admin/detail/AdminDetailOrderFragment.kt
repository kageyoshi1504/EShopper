package com.example.eshopper.admin.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAdminDetailOrderBinding
import com.example.eshopper.databinding.FragmentAdminOrderBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdminDetailOrderFragment : Fragment(R.layout.fragment_admin_detail_order){
  private val binding : FragmentAdminDetailOrderBinding by viewBinding(FragmentAdminDetailOrderBinding::bind)
  

}