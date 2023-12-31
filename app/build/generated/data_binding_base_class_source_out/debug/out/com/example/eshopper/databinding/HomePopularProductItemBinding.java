// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomePopularProductItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatImageView imageProduct;

  @NonNull
  public final TextView nameProduct;

  @NonNull
  public final TextView priceProduct;

  private HomePopularProductItemBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatImageView imageProduct, @NonNull TextView nameProduct,
      @NonNull TextView priceProduct) {
    this.rootView = rootView;
    this.imageProduct = imageProduct;
    this.nameProduct = nameProduct;
    this.priceProduct = priceProduct;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomePopularProductItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomePopularProductItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.home_popular_product_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomePopularProductItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageProduct;
      AppCompatImageView imageProduct = ViewBindings.findChildViewById(rootView, id);
      if (imageProduct == null) {
        break missingId;
      }

      id = R.id.nameProduct;
      TextView nameProduct = ViewBindings.findChildViewById(rootView, id);
      if (nameProduct == null) {
        break missingId;
      }

      id = R.id.priceProduct;
      TextView priceProduct = ViewBindings.findChildViewById(rootView, id);
      if (priceProduct == null) {
        break missingId;
      }

      return new HomePopularProductItemBinding((RelativeLayout) rootView, imageProduct, nameProduct,
          priceProduct);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
