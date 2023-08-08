package com.example.eshopper.users.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class PaymentFragmentDirections private constructor() {
  public companion object {
    public fun actionPaymentFragmentToSuccessPayment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_paymentFragment_to_successPayment)
  }
}
