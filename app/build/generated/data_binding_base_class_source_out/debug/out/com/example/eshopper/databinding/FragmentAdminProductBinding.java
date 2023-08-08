// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAdminProductBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final CardView cardViewHeader;

  @NonNull
  public final TextView emptyProduct;

  @NonNull
  public final AppCompatImageView imageAdd;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final LinearLayout layoutHeader;

  @NonNull
  public final RecyclerView rvProduct;

  @NonNull
  public final ScrollView scroll;

  @NonNull
  public final TextView title;

  private FragmentAdminProductBinding(@NonNull RelativeLayout rootView,
      @NonNull CardView cardViewHeader, @NonNull TextView emptyProduct,
      @NonNull AppCompatImageView imageAdd, @NonNull ImageView imageBack,
      @NonNull LinearLayout layoutHeader, @NonNull RecyclerView rvProduct,
      @NonNull ScrollView scroll, @NonNull TextView title) {
    this.rootView = rootView;
    this.cardViewHeader = cardViewHeader;
    this.emptyProduct = emptyProduct;
    this.imageAdd = imageAdd;
    this.imageBack = imageBack;
    this.layoutHeader = layoutHeader;
    this.rvProduct = rvProduct;
    this.scroll = scroll;
    this.title = title;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAdminProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAdminProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_admin_product, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAdminProductBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardViewHeader;
      CardView cardViewHeader = ViewBindings.findChildViewById(rootView, id);
      if (cardViewHeader == null) {
        break missingId;
      }

      id = R.id.emptyProduct;
      TextView emptyProduct = ViewBindings.findChildViewById(rootView, id);
      if (emptyProduct == null) {
        break missingId;
      }

      id = R.id.imageAdd;
      AppCompatImageView imageAdd = ViewBindings.findChildViewById(rootView, id);
      if (imageAdd == null) {
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

      id = R.id.rvProduct;
      RecyclerView rvProduct = ViewBindings.findChildViewById(rootView, id);
      if (rvProduct == null) {
        break missingId;
      }

      id = R.id.scroll;
      ScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new FragmentAdminProductBinding((RelativeLayout) rootView, cardViewHeader,
          emptyProduct, imageAdd, imageBack, layoutHeader, rvProduct, scroll, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}