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

public final class HomePopularProductBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView rvPopularProduct;

  @NonNull
  public final AppCompatTextView tvPopularProduct;

  private HomePopularProductBinding(@NonNull RelativeLayout rootView,
      @NonNull RecyclerView rvPopularProduct, @NonNull AppCompatTextView tvPopularProduct) {
    this.rootView = rootView;
    this.rvPopularProduct = rvPopularProduct;
    this.tvPopularProduct = tvPopularProduct;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomePopularProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomePopularProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.home_popular_product, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomePopularProductBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvPopularProduct;
      RecyclerView rvPopularProduct = ViewBindings.findChildViewById(rootView, id);
      if (rvPopularProduct == null) {
        break missingId;
      }

      id = R.id.tvPopularProduct;
      AppCompatTextView tvPopularProduct = ViewBindings.findChildViewById(rootView, id);
      if (tvPopularProduct == null) {
        break missingId;
      }

      return new HomePopularProductBinding((RelativeLayout) rootView, rvPopularProduct,
          tvPopularProduct);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
