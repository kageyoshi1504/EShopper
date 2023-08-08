package com.example.eshopper.users.settings

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class ChangeEmailFragmentDirections private constructor() {
  public companion object {
    public fun changeEmailToSettings(): NavDirections =
        ActionOnlyNavDirections(R.id.changeEmailToSettings)
  }
}
