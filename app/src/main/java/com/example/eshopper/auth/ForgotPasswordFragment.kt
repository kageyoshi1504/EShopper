package com.example.eshopper.auth

import android.content.Context
import android.os.Bundle
import android.os.PatternMatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val binding : FragmentForgotPasswordBinding by viewBinding(FragmentForgotPasswordBinding::bind)
    private lateinit var  mAuth : FirebaseAuth
    private lateinit var mContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    private fun initView()
    {
        mAuth = FirebaseAuth.getInstance()
    }
    private fun initAction()
    {
        binding.apply {

            imageBack.setOnClickListener{
                findNavController().popBackStack()
            }
            emailEt.addTextChangedListener(textWatcher)

        }

    }

    private  val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.apply {
                if (emailEt.text.toString().isNotEmpty())
                {
                   if (!Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches())
                   {
                       errorEmail.visibility = View.VISIBLE
                       errorEmail.text = getString(R.string.errorEmail)
                   }
                    else {
                        errorEmail.visibility = View.GONE
                   }

                }
                else {
                    errorEmail.visibility = View.GONE

                }
                reset.isEnabled = Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches()
                if (reset.isEnabled)
                {
                    reset.setOnClickListener {
                        changePassword(emailEt.text.toString())
                    }
                }


            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun changePassword(email : String)
    {
        mAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Common.toast(mContext,"Vui lòng kiểm tra email để thay đổi mật khẩu")
                findNavController().popBackStack()
            }
            .addOnFailureListener{
                binding.errorEmail.visibility = View.VISIBLE
                binding.errorEmail.text = "Email chưa được đăng kí"
            }
    }
}