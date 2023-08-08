package com.example.eshopper.admin.main

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class AdminCategoryFragmentDirections private constructor() {
  private data class AdminCategoryToAdminDetailCategory(
    public val categoryId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.adminCategoryToAdminDetailCategory

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("categoryId", this.categoryId)
        return result
      }
  }

  public companion object {
    public fun adminCategoryToAdminHome(): NavDirections =
        ActionOnlyNavDirections(R.id.adminCategoryToAdminHome)

    public fun adminCategoryToAddCategory(): NavDirections =
        ActionOnlyNavDirections(R.id.adminCategoryToAddCategory)

    public fun adminCategoryToAdminDetailCategory(categoryId: String): NavDirections =
        AdminCategoryToAdminDetailCategory(categoryId)
  }
}
