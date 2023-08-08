package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class InDeliveryFragmentDirections private constructor() {
  private data class DeliveryToDetail(
    public val uid: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.DeliveryToDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("uid", this.uid)
        return result
      }
  }

  public companion object {
    public fun DeliveryToDetail(uid: String): NavDirections = DeliveryToDetail(uid)
  }
}
