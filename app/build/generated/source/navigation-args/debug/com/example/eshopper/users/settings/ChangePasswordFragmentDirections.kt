package com.example.eshopper.users.settings

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class ChangePasswordFragmentDirections private constructor() {
  public companion object {
    public fun changePassToSettings(): NavDirections =
        ActionOnlyNavDirections(R.id.changePassToSettings)
  }
}
