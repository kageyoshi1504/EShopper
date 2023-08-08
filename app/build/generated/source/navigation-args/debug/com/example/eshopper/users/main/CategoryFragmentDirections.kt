package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class CategoryFragmentDirections private constructor() {
  private data class ActionCategoryFragmentToUserFragment(
    public val itemId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_categoryFragment_to_userFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun actionCategoryFragmentToUserFragment(itemId: String): NavDirections =
        ActionCategoryFragmentToUserFragment(itemId)
  }
}
