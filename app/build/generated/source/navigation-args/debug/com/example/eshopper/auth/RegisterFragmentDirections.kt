package com.example.eshopper.auth

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class RegisterFragmentDirections private constructor() {
  private data class RegisterToUser(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.registerToUser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun RegisterToLogin(): NavDirections = ActionOnlyNavDirections(R.id.RegisterToLogin)

    public fun registerToUser(itemId: String): NavDirections = RegisterToUser(itemId)
  }
}
