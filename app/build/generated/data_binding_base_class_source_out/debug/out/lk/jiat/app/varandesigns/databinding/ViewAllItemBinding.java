// Generated by view binder compiler. Do not edit!
package lk.jiat.app.varandesigns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import lk.jiat.app.varandesigns.R;

public final class ViewAllItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView viewAllDes;

  @NonNull
  public final RoundedImageView viewAllImg;

  @NonNull
  public final TextView viewAllName;

  @NonNull
  public final TextView viewAllPrice;

  @NonNull
  public final TextView viewAllRating;

  private ViewAllItemBinding(@NonNull CardView rootView, @NonNull TextView viewAllDes,
      @NonNull RoundedImageView viewAllImg, @NonNull TextView viewAllName,
      @NonNull TextView viewAllPrice, @NonNull TextView viewAllRating) {
    this.rootView = rootView;
    this.viewAllDes = viewAllDes;
    this.viewAllImg = viewAllImg;
    this.viewAllName = viewAllName;
    this.viewAllPrice = viewAllPrice;
    this.viewAllRating = viewAllRating;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewAllItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewAllItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_all_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewAllItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.view_all_des;
      TextView viewAllDes = ViewBindings.findChildViewById(rootView, id);
      if (viewAllDes == null) {
        break missingId;
      }

      id = R.id.view_all_img;
      RoundedImageView viewAllImg = ViewBindings.findChildViewById(rootView, id);
      if (viewAllImg == null) {
        break missingId;
      }

      id = R.id.view_all_name;
      TextView viewAllName = ViewBindings.findChildViewById(rootView, id);
      if (viewAllName == null) {
        break missingId;
      }

      id = R.id.view_all_price;
      TextView viewAllPrice = ViewBindings.findChildViewById(rootView, id);
      if (viewAllPrice == null) {
        break missingId;
      }

      id = R.id.view_all_rating;
      TextView viewAllRating = ViewBindings.findChildViewById(rootView, id);
      if (viewAllRating == null) {
        break missingId;
      }

      return new ViewAllItemBinding((CardView) rootView, viewAllDes, viewAllImg, viewAllName,
          viewAllPrice, viewAllRating);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
