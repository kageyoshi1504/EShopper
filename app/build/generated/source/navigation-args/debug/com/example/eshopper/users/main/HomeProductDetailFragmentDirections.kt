package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class HomeProductDetailFragmentDirections private constructor() {
  private data class HomeProductDetailToUser(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.homeProductDetailToUser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class UserHomeProductDetailToCart(
    public val productId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.userHomeProductDetailToCart

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("productId", this.productId)
        return result
      }
  }

  private data class ProductToPayment(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.productToPayment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun homeProductDetailToUser(itemId: String): NavDirections =
        HomeProductDetailToUser(itemId)

    public fun userHomeProductDetailToCart(productId: String): NavDirections =
        UserHomeProductDetailToCart(productId)

    public fun productToPayment(itemId: String): NavDirections = ProductToPayment(itemId)
  }
}
