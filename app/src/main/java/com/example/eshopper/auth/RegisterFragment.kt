package com.example.eshopper.auth

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    private lateinit var mContext : Context
    private lateinit var dialog : LoadingDialog
    private lateinit var mAuth : FirebaseAuth


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
    }

    private fun initView() {
        dialog = LoadingDialog(childFragmentManager)
        mAuth = FirebaseAuth.getInstance()
    }
    private fun initActions()
    {
        binding.apply {
            imageBack.setOnClickListener{
                findNavController().popBackStack()
            }
            login.setOnClickListener{
                findNavController().navigate(R.id.loginFragment)
            }
            emailEt.addTextChangedListener(textWatcher)
            passwordEt.addTextChangedListener(textWatcher)
            repassEt.addTextChangedListener(textWatcher)

        }
    }
    private val textWatcher = object:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.apply {
                register.isEnabled = Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches() && nameEt.text.toString().isNotEmpty() && passwordEt.text.toString() == repassEt.text.toString() && passwordEt.text.length > 5

                if (emailEt.text.toString().isNotEmpty())
                {
                    if (!Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches())
                    {
                        errorEmail.visibility = View.VISIBLE
                        errorEmail.text = getString(R.string.errorEmail)
                    }
                    else{
                        errorEmail.visibility = View.GONE
                    }
                }
                else{
                    errorEmail.visibility = View.GONE
                }
                if (passwordEt.text.toString().isNotEmpty())
                {
                    if (passwordEt.text.toString().length < 6)
                    {
                        errorPassword.visibility = View.VISIBLE
                        errorPassword.text = "Mật khẩu phải lớn hơn 6 kí tự"
                    }
                    else {
                        errorPassword.visibility = View.GONE

                    }

                    if (passwordEt.text.toString() != repassEt.text.toString() && repassEt.text.toString().isNotEmpty())
                    {
                        errorRePass.visibility = View.VISIBLE
                        errorRePass.text = "Mật khẩu không trùng khớp"
                    }
                    else{
                        errorRePass.visibility = View.GONE

                    }
                }
                else{
                    errorPassword.visibility = View.GONE
                }

                if (register.isEnabled)
                {
                    register.setOnClickListener{
                        register()
                    }
                }

            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    private fun register()
    {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                dialog.show()
                updateInfo()
                Handler().postDelayed({
                    findNavController().navigate(R.id.registerToUser)
                    dialog.safeDismiss()
                }
                    ,3000
                )
            }
            .addOnFailureListener{
                binding.error.visibility = View.VISIBLE
                binding.error.text = getString(R.string.register_failed)
            }
    }

    private fun updateInfo()
    {
        val timestamp = System.currentTimeMillis()
        val uid = mAuth.uid!!
        val info = HashMap<String,Any>()
            info["uid"] = uid
            info["email"] = binding.emailEt.text.toString()
            info["name"] = binding.nameEt.text.toString()
            info["avatar"] =""
            info["userType"]="user"
            info["phone"] = ""
            info["timestamp"]="$timestamp"

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid).setValue(info)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }


}