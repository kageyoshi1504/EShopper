package com.example.eshopper.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class ForgotPasswordFragmentDirections private constructor() {
  public companion object {
    public fun actionForgotPasswordFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_forgotPasswordFragment_to_loginFragment)
  }
}
