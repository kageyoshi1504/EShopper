package com.example.eshopper.users.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentSettingsBinding
import com.example.eshopper.users.main.model.UserModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding : FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mContext : Context


    override fun onResume() {
        super.onResume()
        initData()
        mAuth.currentUser!!.email?.let { Log.d("account", it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
        mAuth.currentUser!!.email?.let { Log.d("account", it) }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    // Tìm hiểu về onView Create hmm

    private fun initView()
    {
        mAuth = FirebaseAuth.getInstance()

    }
    private fun initAction()
    {
        binding.apply {
            imageViewBack.setOnClickListener{
                findNavController().navigate(R.id.settingsToUser)
            }
            cardViewProfile.setOnClickListener{
                findNavController().navigate(R.id.settingToUpdate)
            }
            linearLayoutChangeEmail.setOnClickListener{
                findNavController().navigate(R.id.settingsToChangeEmail)
            }
            linearLayoutChangePassword.setOnClickListener{
                findNavController().navigate(R.id.settingsToChangePass)
            }
            cardViewSignOut.setOnClickListener{
                mAuth.signOut()
                findNavController().navigate(R.id.loginFragment)
              ///  mAuth.currentUser!!.email?.let { Log.d("replay", it) }
            }

            cardViewFb.setOnClickListener {
                openApp(mContext,"https://www.facebook.com/kageyoshi1504")
            }
            cardViewGit.setOnClickListener{
                openApp(mContext,"https://github.com/kageyoshi1504")
            }
            cardViewIns.setOnClickListener {
                openApp(mContext,"https://www.google.com/")
            }
            cardViewYt.setOnClickListener {
                openApp(mContext,"https://www.youtube.com/")
            }

        }
    }


    private fun initData() {
      val user = FirebaseAuth.getInstance().currentUser
        this.binding.apply {
            if (user != null) {
                emailUser.text = user.email
                nameUser.text = user.displayName
                Glide.with(mContext).load(user.photoUrl).into(imageUser)
            }

        }
    }


    private fun openApp(context: Context, link : String){
         if (link.isEmpty())
        {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(link))
            context.startActivity(intent)
        }
        else {
            Common.toast(context,"Khong co url de mo ung dung")
         }
    }



}