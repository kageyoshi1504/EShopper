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
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.eshopper.R;
import com.example.eshopper.common.CustomTabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHistoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final CardView cardViewHeader;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final LinearLayout layoutHeader;

  @NonNull
  public final CustomTabLayout tabLayout;

  @NonNull
  public final TextView title;

  @NonNull
  public final ViewPager2 viewPager2;

  private FragmentHistoryBinding(@NonNull RelativeLayout rootView, @NonNull CardView cardViewHeader,
      @NonNull ImageView imageBack, @NonNull LinearLayout layoutHeader,
      @NonNull CustomTabLayout tabLayout, @NonNull TextView title, @NonNull ViewPager2 viewPager2) {
    this.rootView = rootView;
    this.cardViewHeader = cardViewHeader;
    this.imageBack = imageBack;
    this.layoutHeader = layoutHeader;
    this.tabLayout = tabLayout;
    this.title = title;
    this.viewPager2 = viewPager2;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardViewHeader;
      CardView cardViewHeader = ViewBindings.findChildViewById(rootView, id);
      if (cardViewHeader == null) {
        break missingId;
      }

      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.layoutHeader;
      LinearLayout layoutHeader = ViewBindings.findChildViewById(rootView, id);
      if (layoutHeader == null) {
        break missingId;
      }

      id = R.id.tabLayout;
      CustomTabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      id = R.id.viewPager2;
      ViewPager2 viewPager2 = ViewBindings.findChildViewById(rootView, id);
      if (viewPager2 == null) {
        break missingId;
      }

      return new FragmentHistoryBinding((RelativeLayout) rootView, cardViewHeader, imageBack,
          layoutHeader, tabLayout, title, viewPager2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}