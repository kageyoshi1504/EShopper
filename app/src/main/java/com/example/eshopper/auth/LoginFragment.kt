package com.example.eshopper.auth

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var mContext  : Context
    private lateinit var dialog: LoadingDialog
    private lateinit var mAuth : FirebaseAuth


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initActions()
        disableBackPress()


    }

    private fun initActions() {
        binding.apply {
            emailEt.addTextChangedListener(textWatcher)
            passwordEt.addTextChangedListener(textWatcher)
            register.setOnClickListener{
                findNavController().navigate(R.id.LoginToRegister)
            }
            forgot.setOnClickListener{
                findNavController().navigate(R.id.loginToForgot)
            }
        }

    }
    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        mAuth = FirebaseAuth.getInstance()
    }
    private fun disableBackPress()
    {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        // do nothing

                    }
                }
            )
    }

    private fun login()
    {
        dialog.show()
        mAuth.signInWithEmailAndPassword(binding.emailEt.text.toString(),binding.passwordEt.text.toString())
            .addOnSuccessListener {

                checked()
            }
            .addOnFailureListener{

                  dialog.safeDismiss()
                  binding.errorPassword.visibility = View.VISIBLE
                  binding.errorPassword.text = getString(R.string.loginFailed)
                it.message?.let { it1 -> Log.e("Login", it1) }

            }



    }



    private fun checked(){
        val userAuth = mAuth.currentUser!!
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(userAuth.uid)
                .addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {

                        val userType = snapshot.child("userType").value
                        if (userType == "user")
                        {
                            val bundle = bundleOf("userType" to userType)
                            Handler().postDelayed({
                                findNavController().navigate(R.id.LoginToUser,bundle)
                                dialog.safeDismiss()
                            }
                                ,3000
                            )
                        }
                        else if (userType =="admin") {
                            Handler().postDelayed({
                                findNavController().navigate(R.id.LoginToAdminHome)
                                dialog.safeDismiss()
                            }
                                ,3000
                            )
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

    }
    private val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.apply {
                if (!Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches() && emailEt.text.toString().isNotEmpty())
                {
                    errorEmail.visibility = View.VISIBLE
                    errorEmail.text = getString(R.string.errorEmail)
                }
                else{
                    errorEmail.visibility = View.GONE
                }
                login.isEnabled = Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches() && passwordEt.text.toString().length >= 6
                if (login.isEnabled)
                {
                    login.setOnClickListener{
                        login()
                    }
                }
            }

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }



}