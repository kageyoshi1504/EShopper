package com.example.eshopper.admin.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AdminStaticsFragmentDirections private constructor() {
  public companion object {
    public fun adminStaticsToAdminHome(): NavDirections =
        ActionOnlyNavDirections(R.id.adminStaticsToAdminHome)
  }
}
