package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class CartFragmentDirections private constructor() {
  private data class CartToUserHome(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.cartToUserHome

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class CartToDetail(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.cartToDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class CartToPayment(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.cartToPayment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class ActionUserHomeToCartSelf(
    public val productId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_userHomeToCart_self

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun cartToUserHome(itemId: String): NavDirections = CartToUserHome(itemId)

    public fun cartToDetail(itemId: String): NavDirections = CartToDetail(itemId)

    public fun cartToPayment(itemId: String): NavDirections = CartToPayment(itemId)

    public fun actionUserHomeToCartSelf(productId: String): NavDirections =
        ActionUserHomeToCartSelf(productId)
  }
}
