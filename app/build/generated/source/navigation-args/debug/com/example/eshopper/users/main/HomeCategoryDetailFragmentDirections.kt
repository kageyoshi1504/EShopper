package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class HomeCategoryDetailFragmentDirections private constructor() {
  private data class HomeCategoryDetailToUser(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.homeCategoryDetailToUser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  private data class HomeCategoryDetailToHomeProductDetail(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.homeCategoryDetailToHomeProductDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun homeCategoryDetailToUser(itemId: String): NavDirections =
        HomeCategoryDetailToUser(itemId)

    public fun homeCategoryDetailToHomeProductDetail(itemId: String): NavDirections =
        HomeCategoryDetailToHomeProductDetail(itemId)
  }
}
