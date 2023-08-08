package com.example.eshopper.auth

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class LoginFragmentDirections private constructor() {
  private data class LoginToUser(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.LoginToUser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun LoginToUser(itemId: String): NavDirections = LoginToUser(itemId)

    public fun LoginToRegister(): NavDirections = ActionOnlyNavDirections(R.id.LoginToRegister)

    public fun LoginToAdminHome(): NavDirections = ActionOnlyNavDirections(R.id.LoginToAdminHome)

    public fun loginToForgot(): NavDirections = ActionOnlyNavDirections(R.id.loginToForgot)
  }
}
