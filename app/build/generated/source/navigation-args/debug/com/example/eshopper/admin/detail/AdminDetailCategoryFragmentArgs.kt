package com.example.eshopper.admin.detail

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class AdminDetailCategoryFragmentArgs(
  public val categoryId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("categoryId", this.categoryId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("categoryId", this.categoryId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): AdminDetailCategoryFragmentArgs {
      bundle.setClassLoader(AdminDetailCategoryFragmentArgs::class.java.classLoader)
      val __categoryId : String?
      if (bundle.containsKey("categoryId")) {
        __categoryId = bundle.getString("categoryId")
        if (__categoryId == null) {
          throw IllegalArgumentException("Argument \"categoryId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"categoryId\" is missing and does not have an android:defaultValue")
      }
      return AdminDetailCategoryFragmentArgs(__categoryId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        AdminDetailCategoryFragmentArgs {
      val __categoryId : String?
      if (savedStateHandle.contains("categoryId")) {
        __categoryId = savedStateHandle["categoryId"]
        if (__categoryId == null) {
          throw IllegalArgumentException("Argument \"categoryId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"categoryId\" is missing and does not have an android:defaultValue")
      }
      return AdminDetailCategoryFragmentArgs(__categoryId)
    }
  }
}
