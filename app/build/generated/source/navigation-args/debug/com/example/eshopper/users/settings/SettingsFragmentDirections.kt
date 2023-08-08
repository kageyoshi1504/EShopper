package com.example.eshopper.users.settings

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class SettingsFragmentDirections private constructor() {
  private data class SettingsToUser(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.settingsToUser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun settingsToUser(itemId: String): NavDirections = SettingsToUser(itemId)

    public fun settingToUpdate(): NavDirections = ActionOnlyNavDirections(R.id.settingToUpdate)

    public fun settingsToLogin(): NavDirections = ActionOnlyNavDirections(R.id.settingsToLogin)

    public fun settingsToChangePass(): NavDirections =
        ActionOnlyNavDirections(R.id.settingsToChangePass)

    public fun settingsToChangeEmail(): NavDirections =
        ActionOnlyNavDirections(R.id.settingsToChangeEmail)
  }
}
