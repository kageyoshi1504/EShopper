package com.example.eshopper.admin.main

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class AdminProductFragmentDirections private constructor() {
  private data class AdminProductToAdminDetailProduct(
    public val productId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.adminProductToAdminDetailProduct

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun AdminProductToAdminHome(): NavDirections =
        ActionOnlyNavDirections(R.id.AdminProductToAdminHome)

    public fun adminProductToAddProduct(): NavDirections =
        ActionOnlyNavDirections(R.id.adminProductToAddProduct)

    public fun adminProductToAdminDetailProduct(productId: String): NavDirections =
        AdminProductToAdminDetailProduct(productId)
  }
}
