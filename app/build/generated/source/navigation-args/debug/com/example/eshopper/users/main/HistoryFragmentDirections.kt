package com.example.eshopper.users.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.eshopper.R

public class HistoryFragmentDirections private constructor() {
  public companion object {
    public fun actionHistoryFragmentToCancelledFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_historyFragment_to_cancelledFragment)

    public fun actionHistoryFragmentToInDeliveryFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_historyFragment_to_inDeliveryFragment)

    public fun actionHistoryFragmentToReceiveFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_historyFragment_to_receiveFragment)

    public fun actionHistoryFragmentToPendingFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_historyFragment_to_pendingFragment)
  }
}
