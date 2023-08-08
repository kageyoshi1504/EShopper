package com.example.eshopper.users.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private val binding : FragmentChangePasswordBinding by viewBinding(FragmentChangePasswordBinding::bind)
    private lateinit var mContext : Context
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dialog : LoadingDialog

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
        dialog = LoadingDialog(childFragmentManager)
        mAuth = FirebaseAuth.getInstance()


    }

    private fun initAction()
    {
            binding.apply {
               imageViewBack.setOnClickListener {
                   findNavController().popBackStack()
               }

                buttonSave.setOnClickListener {
                    if (validate())
                    {
                        changePassword(editTextCurrentPassword.text.toString(),editTextNewPassword.text.toString())

                    }
                }
            }
    }

    private fun validate() :Boolean
    {

        binding.apply {
            if (editTextCurrentPassword.text.toString().isEmpty() || editTextNewPassword.text.toString().isEmpty() || editTextConfirmPassword.text.toString().isEmpty())
            {
                    textViewError.visibility = View.VISIBLE
                    textViewError.text = "Không được để trống thông tin"
                    reset()
                    return false
            }

            if (editTextNewPassword.text.toString() != editTextConfirmPassword.text.toString())
            {
                textViewError.visibility = View.VISIBLE
                textViewError.text = "Mật khẩu không trùng khớp"
                reset()
                return false
            }

            if (editTextCurrentPassword.text.toString() == editTextNewPassword.text.toString())
            {
                textViewError.visibility = View.VISIBLE
                textViewError.text = "Mật khẩu mới không được giống mật khẩu cũ"
                reset()
                return false
            }
         }

            return true
    }
    private fun changePassword(currentPassword : String , newPassword : String) {
        val user = mAuth.currentUser
        dialog.show()

        if (user!= null)
        {
            val credentials = EmailAuthProvider.getCredential(user.email!!,currentPassword)
            user.reauthenticate(credentials)
                .addOnSuccessListener {
                    user.updatePassword(newPassword)
                        .addOnSuccessListener {
                            dialog.safeDismiss()
                            Common.toast(mContext,"Thay đổi mật khẩu thành công!!")
                        }
                        .addOnFailureListener{
                            dialog.safeDismiss()
                            Common.toast(mContext,"Thay đổi mật khẩu thất bại")
                        }
                }
        }

    }
    private fun reset()
    {
        binding.apply {
            editTextConfirmPassword.setText("")
            editTextCurrentPassword.setText("")
            editTextNewPassword.setText("")
        }
    }

}