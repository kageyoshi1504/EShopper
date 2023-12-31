// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomeHotDealProductBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView rvHotDeal;

  @NonNull
  public final AppCompatTextView tvHotDeal;

  private HomeHotDealProductBinding(@NonNull RelativeLayout rootView,
      @NonNull RecyclerView rvHotDeal, @NonNull AppCompatTextView tvHotDeal) {
    this.rootView = rootView;
    this.rvHotDeal = rvHotDeal;
    this.tvHotDeal = tvHotDeal;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomeHotDealProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomeHotDealProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.home_hot_deal_product, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomeHotDealProductBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvHotDeal;
      RecyclerView rvHotDeal = ViewBindings.findChildViewById(rootView, id);
      if (rvHotDeal == null) {
        break missingId;
      }

      id = R.id.tvHotDeal;
      AppCompatTextView tvHotDeal = ViewBindings.findChildViewById(rootView, id);
      if (tvHotDeal == null) {
        break missingId;
      }

      return new HomeHotDealProductBinding((RelativeLayout) rootView, rvHotDeal, tvHotDeal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
