package lk.jiat.app.varandesigns.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.modals.Popular;
import lk.jiat.app.varandesigns.modals.Recommended;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class SingleViewActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView singleViewImage;
    TextView price,rating,description;
    Button addToCart;

    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ViewAll viewAll = null;
    Recommended recommended = null;
    Popular popular = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAll) {
            viewAll = (ViewAll) object;
        }
        if (object instanceof Recommended) {
            recommended = (Recommended) object;
        }
        if (object instanceof Popular) {
            popular = (Popular) object;
        }

        singleViewImage = findViewById(R.id.single_view_image);

        price = findViewById(R.id.single_view_price);
        rating = findViewById(R.id.single_view_rating);
        description = findViewById(R.id.single_view_description);

        if (viewAll != null){
            Glide.with(getApplicationContext()).load(viewAll.getImg_url()).into(singleViewImage);
            rating.setText(viewAll.getRating());
            description.setText(viewAll.getDescription());
            price.setText("Price : Rs."+viewAll.getPrice()+"/=");

            totalPrice = viewAll.getPrice();

            if (viewAll.getCategory().equals("Vector")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
            if (viewAll.getCategory().equals("3DModal")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
            if (viewAll.getCategory().equals("3DCharacter")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
            if (viewAll.getCategory().equals("2DDesign")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
            if (viewAll.getCategory().equals("Drawings")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
            if (viewAll.getCategory().equals("ArtWork")){
                price.setText("Rs."+viewAll.getPrice()+"/=");
            }
        }

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });



    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM.dd.yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("designName",viewAll.getName());
        cartMap.put("designPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(SingleViewActivity.this,"Added To a Cart",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}