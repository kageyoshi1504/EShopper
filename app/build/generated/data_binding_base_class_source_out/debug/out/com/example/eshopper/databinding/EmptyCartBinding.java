// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EmptyCartBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton backHome;

  @NonNull
  public final TextView desc;

  @NonNull
  public final ImageView emptyImage;

  @NonNull
  public final TextView title;

  private EmptyCartBinding(@NonNull RelativeLayout rootView, @NonNull AppCompatButton backHome,
      @NonNull TextView desc, @NonNull ImageView emptyImage, @NonNull TextView title) {
    this.rootView = rootView;
    this.backHome = backHome;
    this.desc = desc;
    this.emptyImage = emptyImage;
    this.title = title;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static EmptyCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EmptyCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.empty_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EmptyCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backHome;
      AppCompatButton backHome = ViewBindings.findChildViewById(rootView, id);
      if (backHome == null) {
        break missingId;
      }

      id = R.id.desc;
      TextView desc = ViewBindings.findChildViewById(rootView, id);
      if (desc == null) {
        break missingId;
      }

      id = R.id.emptyImage;
      ImageView emptyImage = ViewBindings.findChildViewById(rootView, id);
      if (emptyImage == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new EmptyCartBinding((RelativeLayout) rootView, backHome, desc, emptyImage, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
