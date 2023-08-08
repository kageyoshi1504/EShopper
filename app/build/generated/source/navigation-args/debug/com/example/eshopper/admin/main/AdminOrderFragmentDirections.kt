package com.example.eshopper.admin.main

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class AdminOrderFragmentDirections private constructor() {
  private data class ActionAdminOrderFragmentToOrderDetailFragment(
    public val uid: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_adminOrderFragment_to_orderDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("uid", this.uid)
        return result
      }
  }

  public companion object {
    public fun adminOrderToAdminHome(): NavDirections =
        ActionOnlyNavDirections(R.id.adminOrderToAdminHome)

    public fun actionAdminOrderFragmentToOrderDetailFragment(uid: String): NavDirections =
        ActionAdminOrderFragmentToOrderDetailFragment(uid)
  }
}
