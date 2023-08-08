package com.example.eshopper.admin.detail

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AdminDetailProductFragmentDirections private constructor() {
  public companion object {
    public fun adminDetailProductToAdminProduct(): NavDirections =
        ActionOnlyNavDirections(R.id.adminDetailProductToAdminProduct)
  }
}
