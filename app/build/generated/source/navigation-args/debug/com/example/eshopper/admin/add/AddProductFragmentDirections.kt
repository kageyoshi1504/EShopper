package com.example.eshopper.admin.add

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AddProductFragmentDirections private constructor() {
  public companion object {
    public fun addProductToAdminProduct(): NavDirections =
        ActionOnlyNavDirections(R.id.addProductToAdminProduct)
  }
}
