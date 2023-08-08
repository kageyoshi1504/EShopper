// Generated by view binder compiler. Do not edit!
package com.example.eshopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPaymentBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText addressEt;

  @NonNull
  public final CardView cardViewHeader;

  @NonNull
  public final CardView cardViewPayment;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final LinearLayout layoutAddress;

  @NonNull
  public final LinearLayout layoutHeader;

  @NonNull
  public final LinearLayout layoutMethod;

  @NonNull
  public final LinearLayout layoutName;

  @NonNull
  public final LinearLayout layoutNumber;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final EditText methodEt;

  @NonNull
  public final EditText nameEt;

  @NonNull
  public final TextView order;

  @NonNull
  public final EditText phoneEt;

  @NonNull
  public final TextView product;

  @NonNull
  public final RelativeLayout relative;

  @NonNull
  public final RecyclerView rvPayment;

  @NonNull
  public final TextView sumPayment;

  @NonNull
  public final TextView title;

  @NonNull
  public final TextView totalPrice;

  @NonNull
  public final TextView tvMethod;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvNumber;

  @NonNull
  public final TextView tvadress;

  private FragmentPaymentBinding(@NonNull RelativeLayout rootView, @NonNull EditText addressEt,
      @NonNull CardView cardViewHeader, @NonNull CardView cardViewPayment,
      @NonNull ImageView imageBack, @NonNull LinearLayout layoutAddress,
      @NonNull LinearLayout layoutHeader, @NonNull LinearLayout layoutMethod,
      @NonNull LinearLayout layoutName, @NonNull LinearLayout layoutNumber,
      @NonNull RelativeLayout main, @NonNull EditText methodEt, @NonNull EditText nameEt,
      @NonNull TextView order, @NonNull EditText phoneEt, @NonNull TextView product,
      @NonNull RelativeLayout relative, @NonNull RecyclerView rvPayment,
      @NonNull TextView sumPayment, @NonNull TextView title, @NonNull TextView totalPrice,
      @NonNull TextView tvMethod, @NonNull TextView tvName, @NonNull TextView tvNumber,
      @NonNull TextView tvadress) {
    this.rootView = rootView;
    this.addressEt = addressEt;
    this.cardViewHeader = cardViewHeader;
    this.cardViewPayment = cardViewPayment;
    this.imageBack = imageBack;
    this.layoutAddress = layoutAddress;
    this.layoutHeader = layoutHeader;
    this.layoutMethod = layoutMethod;
    this.layoutName = layoutName;
    this.layoutNumber = layoutNumber;
    this.main = main;
    this.methodEt = methodEt;
    this.nameEt = nameEt;
    this.order = order;
    this.phoneEt = phoneEt;
    this.product = product;
    this.relative = relative;
    this.rvPayment = rvPayment;
    this.sumPayment = sumPayment;
    this.title = title;
    this.totalPrice = totalPrice;
    this.tvMethod = tvMethod;
    this.tvName = tvName;
    this.tvNumber = tvNumber;
    this.tvadress = tvadress;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPaymentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPaymentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_payment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPaymentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addressEt;
      EditText addressEt = ViewBindings.findChildViewById(rootView, id);
      if (addressEt == null) {
        break missingId;
      }

      id = R.id.cardViewHeader;
      CardView cardViewHeader = ViewBindings.findChildViewById(rootView, id);
      if (cardViewHeader == null) {
        break missingId;
      }

      id = R.id.cardViewPayment;
      CardView cardViewPayment = ViewBindings.findChildViewById(rootView, id);
      if (cardViewPayment == null) {
        break missingId;
      }

      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.layout_address;
      LinearLayout layoutAddress = ViewBindings.findChildViewById(rootView, id);
      if (layoutAddress == null) {
        break missingId;
      }

      id = R.id.layoutHeader;
      LinearLayout layoutHeader = ViewBindings.findChildViewById(rootView, id);
      if (layoutHeader == null) {
        break missingId;
      }

      id = R.id.layout_method;
      LinearLayout layoutMethod = ViewBindings.findChildViewById(rootView, id);
      if (layoutMethod == null) {
        break missingId;
      }

      id = R.id.layout_name;
      LinearLayout layoutName = ViewBindings.findChildViewById(rootView, id);
      if (layoutName == null) {
        break missingId;
      }

      id = R.id.layout_number;
      LinearLayout layoutNumber = ViewBindings.findChildViewById(rootView, id);
      if (layoutNumber == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.methodEt;
      EditText methodEt = ViewBindings.findChildViewById(rootView, id);
      if (methodEt == null) {
        break missingId;
      }

      id = R.id.nameEt;
      EditText nameEt = ViewBindings.findChildViewById(rootView, id);
      if (nameEt == null) {
        break missingId;
      }

      id = R.id.order;
      TextView order = ViewBindings.findChildViewById(rootView, id);
      if (order == null) {
        break missingId;
      }

      id = R.id.phoneEt;
      EditText phoneEt = ViewBindings.findChildViewById(rootView, id);
      if (phoneEt == null) {
        break missingId;
      }

      id = R.id.product;
      TextView product = ViewBindings.findChildViewById(rootView, id);
      if (product == null) {
        break missingId;
      }

      id = R.id.relative;
      RelativeLayout relative = ViewBindings.findChildViewById(rootView, id);
      if (relative == null) {
        break missingId;
      }

      id = R.id.rvPayment;
      RecyclerView rvPayment = ViewBindings.findChildViewById(rootView, id);
      if (rvPayment == null) {
        break missingId;
      }

      id = R.id.sum_payment;
      TextView sumPayment = ViewBindings.findChildViewById(rootView, id);
      if (sumPayment == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      id = R.id.totalPrice;
      TextView totalPrice = ViewBindings.findChildViewById(rootView, id);
      if (totalPrice == null) {
        break missingId;
      }

      id = R.id.tvMethod;
      TextView tvMethod = ViewBindings.findChildViewById(rootView, id);
      if (tvMethod == null) {
        break missingId;
      }

      id = R.id.tvName;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tvNumber;
      TextView tvNumber = ViewBindings.findChildViewById(rootView, id);
      if (tvNumber == null) {
        break missingId;
      }

      id = R.id.tvadress;
      TextView tvadress = ViewBindings.findChildViewById(rootView, id);
      if (tvadress == null) {
        break missingId;
      }

      return new FragmentPaymentBinding((RelativeLayout) rootView, addressEt, cardViewHeader,
          cardViewPayment, imageBack, layoutAddress, layoutHeader, layoutMethod, layoutName,
          layoutNumber, main, methodEt, nameEt, order, phoneEt, product, relative, rvPayment,
          sumPayment, title, totalPrice, tvMethod, tvName, tvNumber, tvadress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}