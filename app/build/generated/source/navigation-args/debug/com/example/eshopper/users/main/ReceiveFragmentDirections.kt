package com.example.eshopper.users.main

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.eshopper.R
import kotlin.Int
import kotlin.String

public class ReceiveFragmentDirections private constructor() {
  private data class ReceiverToDetail(
    public val uid: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.ReceiverToDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("uid", this.uid)
        return result
      }
  }

  public companion object {
    public fun ReceiverToDetail(uid: String): NavDirections = ReceiverToDetail(uid)
  }
}
