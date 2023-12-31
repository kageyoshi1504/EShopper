// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAdminStaticsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView hoadon;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final LinearLayout l1;

  @NonNull
  public final LinearLayout layoutHeader;

  @NonNull
  public final View line;

  @NonNull
  public final MaterialCardView order;

  @NonNull
  public final MaterialCardView statistic;

  @NonNull
  public final TextView thongke;

  private FragmentAdminStaticsBinding(@NonNull RelativeLayout rootView, @NonNull TextView hoadon,
      @NonNull ImageView imageBack, @NonNull LinearLayout l1, @NonNull LinearLayout layoutHeader,
      @NonNull View line, @NonNull MaterialCardView order, @NonNull MaterialCardView statistic,
      @NonNull TextView thongke) {
    this.rootView = rootView;
    this.hoadon = hoadon;
    this.imageBack = imageBack;
    this.l1 = l1;
    this.layoutHeader = layoutHeader;
    this.line = line;
    this.order = order;
    this.statistic = statistic;
    this.thongke = thongke;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAdminStaticsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAdminStaticsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_admin_statics, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAdminStaticsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hoadon;
      TextView hoadon = ViewBindings.findChildViewById(rootView, id);
      if (hoadon == null) {
        break missingId;
      }

      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.l1;
      LinearLayout l1 = ViewBindings.findChildViewById(rootView, id);
      if (l1 == null) {
        break missingId;
      }

      id = R.id.layoutHeader;
      LinearLayout layoutHeader = ViewBindings.findChildViewById(rootView, id);
      if (layoutHeader == null) {
        break missingId;
      }

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.order;
      MaterialCardView order = ViewBindings.findChildViewById(rootView, id);
      if (order == null) {
        break missingId;
      }

      id = R.id.statistic;
      MaterialCardView statistic = ViewBindings.findChildViewById(rootView, id);
      if (statistic == null) {
        break missingId;
      }

      id = R.id.thongke;
      TextView thongke = ViewBindings.findChildViewById(rootView, id);
      if (thongke == null) {
        break missingId;
      }

      return new FragmentAdminStaticsBinding((RelativeLayout) rootView, hoadon, imageBack, l1,
          layoutHeader, line, order, statistic, thongke);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
