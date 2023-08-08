package com.example.eshopper.admin.add

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class AddCategoryFragmentDirections private constructor() {
  public companion object {
    public fun addCategoryToAdminCategory(): NavDirections =
        ActionOnlyNavDirections(R.id.addCategoryToAdminCategory)
  }
}
