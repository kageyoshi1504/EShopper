package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class UserToHomeCategoryDetail(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.userToHomeCategoryDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class UserToCart(
    public val productId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.userToCart

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("productId", this.productId)
        return result
      }
  }

  private data class UserToHomeProductDetail(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.userToHomeProductDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun userFragmentToUserCategory(): NavDirections =
        ActionOnlyNavDirections(R.id.userFragmentToUserCategory)

    public fun userFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.userFragmentToLoginFragment)

    public fun userToHomeCategoryDetail(itemId: String): NavDirections =
        UserToHomeCategoryDetail(itemId)

    public fun userToCart(productId: String): NavDirections = UserToCart(productId)

    public fun userToHomeProductDetail(itemId: String): NavDirections =
        UserToHomeProductDetail(itemId)

    public fun userToHistory(): NavDirections = ActionOnlyNavDirections(R.id.userToHistory)
  }
}
