package com.example.eshopper.admin.detail

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AdminDetailCategoryFragmentDirections private constructor() {
  public companion object {
    public fun adminDetailCategoryToAdminCategory(): NavDirections =
        ActionOnlyNavDirections(R.id.adminDetailCategoryToAdminCategory)
  }
}
