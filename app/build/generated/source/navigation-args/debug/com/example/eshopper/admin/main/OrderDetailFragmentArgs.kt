package com.example.eshopper.admin.main

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class OrderDetailFragmentArgs(
  public val uid: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("uid", this.uid)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("uid", this.uid)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): OrderDetailFragmentArgs {
      bundle.setClassLoader(OrderDetailFragmentArgs::class.java.classLoader)
      val __uid : String?
      if (bundle.containsKey("uid")) {
        __uid = bundle.getString("uid")
        if (__uid == null) {
          throw IllegalArgumentException("Argument \"uid\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"uid\" is missing and does not have an android:defaultValue")
      }
      return OrderDetailFragmentArgs(__uid)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): OrderDetailFragmentArgs {
      val __uid : String?
      if (savedStateHandle.contains("uid")) {
        __uid = savedStateHandle["uid"]
        if (__uid == null) {
          throw IllegalArgumentException("Argument \"uid\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"uid\" is missing and does not have an android:defaultValue")
      }
      return OrderDetailFragmentArgs(__uid)
    }
  }
}
