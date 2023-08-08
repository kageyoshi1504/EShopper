package com.example.eshopper.users.settings

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class UpdateProfileFragmentDirections private constructor() {
  public companion object {
    public fun updateToSettings(): NavDirections = ActionOnlyNavDirections(R.id.updateToSettings)
  }
}
