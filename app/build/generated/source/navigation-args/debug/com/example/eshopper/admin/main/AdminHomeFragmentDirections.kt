package com.example.eshopper.admin.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AdminHomeFragmentDirections private constructor() {
  public companion object {
    public fun adminHomeToAdminCategory(): NavDirections =
        ActionOnlyNavDirections(R.id.adminHomeToAdminCategory)

    public fun adminHomeToAdminProduct(): NavDirections =
        ActionOnlyNavDirections(R.id.adminHomeToAdminProduct)

    public fun adminHomeToAdminStatics(): NavDirections =
        ActionOnlyNavDirections(R.id.adminHomeToAdminStatics)

    public fun adminHomeToAdminOrder(): NavDirections =
        ActionOnlyNavDirections(R.id.adminHomeToAdminOrder)
  }
}
