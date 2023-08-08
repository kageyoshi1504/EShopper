package com.example.eshopper.users.settings

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentChangeEmailBinding
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.net.PasswordAuthentication


class ChangeEmailFragment : Fragment(R.layout.fragment_change_email) {
    private val binding : FragmentChangeEmailBinding by viewBinding(FragmentChangeEmailBinding::bind)
    private lateinit var mContext : Context
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dialog : LoadingDialog
    private lateinit var dbRef : DatabaseReference




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
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        binding.textViewCurrentEmail.text = mAuth.currentUser!!.email

    }


    private fun initAction()
    {
        binding.apply {
            imageViewBack.setOnClickListener{
                findNavController().popBackStack()
            }
            buttonSave.setOnClickListener {
                if (validate(binding.editTextNewEmail.text.toString()))
                {
                    updateEmail(binding.editTextNewEmail.text.toString())
                }

            }

        }
    }
    private fun validate(newEmail: String) : Boolean
    {
        binding.apply {
            if (TextUtils.isEmpty(editTextNewEmail.text.toString()))
            {
                textViewError.visibility = View.VISIBLE
                textViewError.text ="Vui lòng nhập email"
                return false
            }
            if (editTextPassword.text.toString().isEmpty())
            {
                textViewError.visibility = View.VISIBLE
                textViewError.text = "Vui lòng nhập password"
            }
            if (textViewCurrentEmail.text.toString() == newEmail)
            {
                textViewError.visibility = View.VISIBLE
                textViewError.text ="Email mới trùng với email hiện tại"
                editTextPassword.setText("")
                editTextNewEmail.setText("")
                return false
            }
        }

        return true
    }
    private fun updateEmail(newEmail : String)
    {
        dialog.show()
        val user = mAuth.currentUser
        user?.let{
            val credential = EmailAuthProvider.getCredential(user.email!!,binding.editTextPassword.text.toString())
            user.reauthenticate(credential)
                .addOnSuccessListener {
                    user.updateEmail(newEmail).addOnCompleteListener{
                            task->
                        if (task.isSuccessful)
                        {

                            val update = HashMap<String,Any>()
                            update["email"] = newEmail
                            dbRef.child(user.uid)
                                .updateChildren(update)
                                .addOnSuccessListener {
                                    reset()
                                    dialog.safeDismiss()
                                    Common.toast(mContext,"Thay đổi email thành công!!")
                                }   .addOnFailureListener{
                                    dialog.safeDismiss()
                                    Common.toast(mContext,"Thay đổi email thất bại")
                                }
                        }
                }

        }
                .addOnFailureListener{
                    dialog.safeDismiss()
                   binding.apply {
                       textViewError.visibility = View.VISIBLE
                       textViewError.text = "Sai mật khẩu"
                   }

                }

        }
    }

    private fun reset()
    {
        binding.apply {
            editTextNewEmail.setText("")
            editTextPassword.setText("")
            textViewError.visibility = View.GONE
        }
    }

}