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
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.eshopper.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText emailEt;

  @NonNull
  public final TextView error;

  @NonNull
  public final TextView errorEmail;

  @NonNull
  public final TextView errorPassword;

  @NonNull
  public final TextView errorRePass;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final LinearLayout layout;

  @NonNull
  public final TextInputLayout layoutEmail;

  @NonNull
  public final TextInputLayout layoutName;

  @NonNull
  public final TextInputLayout layoutPassword;

  @NonNull
  public final TextInputLayout layoutRePass;

  @NonNull
  public final View line;

  @NonNull
  public final TextView login;

  @NonNull
  public final EditText nameEt;

  @NonNull
  public final EditText passwordEt;

  @NonNull
  public final AppCompatButton register;

  @NonNull
  public final EditText repassEt;

  @NonNull
  public final TextView title;

  private FragmentRegisterBinding(@NonNull RelativeLayout rootView, @NonNull EditText emailEt,
      @NonNull TextView error, @NonNull TextView errorEmail, @NonNull TextView errorPassword,
      @NonNull TextView errorRePass, @NonNull ImageView imageBack, @NonNull LinearLayout layout,
      @NonNull TextInputLayout layoutEmail, @NonNull TextInputLayout layoutName,
      @NonNull TextInputLayout layoutPassword, @NonNull TextInputLayout layoutRePass,
      @NonNull View line, @NonNull TextView login, @NonNull EditText nameEt,
      @NonNull EditText passwordEt, @NonNull AppCompatButton register, @NonNull EditText repassEt,
      @NonNull TextView title) {
    this.rootView = rootView;
    this.emailEt = emailEt;
    this.error = error;
    this.errorEmail = errorEmail;
    this.errorPassword = errorPassword;
    this.errorRePass = errorRePass;
    this.imageBack = imageBack;
    this.layout = layout;
    this.layoutEmail = layoutEmail;
    this.layoutName = layoutName;
    this.layoutPassword = layoutPassword;
    this.layoutRePass = layoutRePass;
    this.line = line;
    this.login = login;
    this.nameEt = nameEt;
    this.passwordEt = passwordEt;
    this.register = register;
    this.repassEt = repassEt;
    this.title = title;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailEt;
      EditText emailEt = ViewBindings.findChildViewById(rootView, id);
      if (emailEt == null) {
        break missingId;
      }

      id = R.id.error;
      TextView error = ViewBindings.findChildViewById(rootView, id);
      if (error == null) {
        break missingId;
      }

      id = R.id.errorEmail;
      TextView errorEmail = ViewBindings.findChildViewById(rootView, id);
      if (errorEmail == null) {
        break missingId;
      }

      id = R.id.errorPassword;
      TextView errorPassword = ViewBindings.findChildViewById(rootView, id);
      if (errorPassword == null) {
        break missingId;
      }

      id = R.id.errorRePass;
      TextView errorRePass = ViewBindings.findChildViewById(rootView, id);
      if (errorRePass == null) {
        break missingId;
      }

      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.layout;
      LinearLayout layout = ViewBindings.findChildViewById(rootView, id);
      if (layout == null) {
        break missingId;
      }

      id = R.id.layoutEmail;
      TextInputLayout layoutEmail = ViewBindings.findChildViewById(rootView, id);
      if (layoutEmail == null) {
        break missingId;
      }

      id = R.id.layoutName;
      TextInputLayout layoutName = ViewBindings.findChildViewById(rootView, id);
      if (layoutName == null) {
        break missingId;
      }

      id = R.id.layoutPassword;
      TextInputLayout layoutPassword = ViewBindings.findChildViewById(rootView, id);
      if (layoutPassword == null) {
        break missingId;
      }

      id = R.id.layoutRePass;
      TextInputLayout layoutRePass = ViewBindings.findChildViewById(rootView, id);
      if (layoutRePass == null) {
        break missingId;
      }

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.login;
      TextView login = ViewBindings.findChildViewById(rootView, id);
      if (login == null) {
        break missingId;
      }

      id = R.id.nameEt;
      EditText nameEt = ViewBindings.findChildViewById(rootView, id);
      if (nameEt == null) {
        break missingId;
      }

      id = R.id.passwordEt;
      EditText passwordEt = ViewBindings.findChildViewById(rootView, id);
      if (passwordEt == null) {
        break missingId;
      }

      id = R.id.register;
      AppCompatButton register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      id = R.id.repassEt;
      EditText repassEt = ViewBindings.findChildViewById(rootView, id);
      if (repassEt == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new FragmentRegisterBinding((RelativeLayout) rootView, emailEt, error, errorEmail,
          errorPassword, errorRePass, imageBack, layout, layoutEmail, layoutName, layoutPassword,
          layoutRePass, line, login, nameEt, passwordEt, register, repassEt, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
