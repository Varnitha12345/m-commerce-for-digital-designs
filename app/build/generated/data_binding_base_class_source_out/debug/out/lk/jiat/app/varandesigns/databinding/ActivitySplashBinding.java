// Generated by view binder compiler. Do not edit!
package lk.jiat.app.varandesigns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import lk.jiat.app.varandesigns.R;

public final class ActivitySplashBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView signInLink;

  @NonNull
  public final TextView signInQue;

  @NonNull
  public final SpinKitView spinKit;

  @NonNull
  public final ImageView splashView;

  @NonNull
  public final Button startShoppingBtn;

  private ActivitySplashBinding(@NonNull FrameLayout rootView, @NonNull TextView signInLink,
      @NonNull TextView signInQue, @NonNull SpinKitView spinKit, @NonNull ImageView splashView,
      @NonNull Button startShoppingBtn) {
    this.rootView = rootView;
    this.signInLink = signInLink;
    this.signInQue = signInQue;
    this.spinKit = spinKit;
    this.splashView = splashView;
    this.startShoppingBtn = startShoppingBtn;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.sign_in_link;
      TextView signInLink = ViewBindings.findChildViewById(rootView, id);
      if (signInLink == null) {
        break missingId;
      }

      id = R.id.sign_in_que;
      TextView signInQue = ViewBindings.findChildViewById(rootView, id);
      if (signInQue == null) {
        break missingId;
      }

      id = R.id.spin_kit;
      SpinKitView spinKit = ViewBindings.findChildViewById(rootView, id);
      if (spinKit == null) {
        break missingId;
      }

      id = R.id.splash_view;
      ImageView splashView = ViewBindings.findChildViewById(rootView, id);
      if (splashView == null) {
        break missingId;
      }

      id = R.id.start_shopping_btn;
      Button startShoppingBtn = ViewBindings.findChildViewById(rootView, id);
      if (startShoppingBtn == null) {
        break missingId;
      }

      return new ActivitySplashBinding((FrameLayout) rootView, signInLink, signInQue, spinKit,
          splashView, startShoppingBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
